package com.midas.springjpa.web;

import com.midas.springjpa.domain.posts.Posts;
import com.midas.springjpa.domain.posts.PostsRepository;
import com.midas.springjpa.web.dto.posts.PostsSaveRequestDto;
import com.midas.springjpa.web.dto.posts.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;

    @Autowired private PostsRepository postsRepository;

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정된다() throws Exception {
        // given
        String title = "title";
        String content = "content";

        Posts savedPosts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("midas")
                .build());

        Long updateId = savedPosts.getId();

        String updateTitle = "updatedTitle";
        String updateContent = "updatedContent";

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        // when
        restTemplate.put(url, requestDto);

        Posts findOne = postsRepository.findById(updateId).get();

        // then
        assertThat(findOne.getTitle()).isEqualTo(updateTitle);
        assertThat(findOne.getContent()).isEqualTo(updateContent);
    }
    
    @Test
    public void Posts_삭제된다() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("midas")
                .build());

        Long deleteId = savedPosts.getId();

        String url = "http://localhost:" + port + "/api/v1/posts/" + deleteId;

        // when
        restTemplate.delete(url);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            postsRepository.findById(deleteId).orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다. id=" + deleteId));
        });
        
    }
}