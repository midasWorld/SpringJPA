package com.midas.springjpa.service;

import com.midas.springjpa.domain.auth.Member;
import com.midas.springjpa.domain.auth.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Member join(Member member) {
    return memberRepository.save(member);
  }
}
