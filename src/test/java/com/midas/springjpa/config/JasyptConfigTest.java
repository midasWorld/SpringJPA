package com.midas.springjpa.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JasyptConfigTest {

    PooledPBEStringEncryptor encryptor;
    
    @Test
    public void 코드_암호화하기(){
        // given
        String key = "key"; // key
        String url = "// url"; // url
        String username = "username"; // username
        String password = "password"; // password

        setup(key);
        // when
        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

        System.out.println("encryptUrl: " + encryptUrl);
        System.out.println("encryptUsername: " + encryptUsername);
        System.out.println("encryptPassword: " + encryptPassword);

        // then
        assertThat(jasyptDecrypt(encryptUrl)).isEqualTo(url);
        assertThat(jasyptDecrypt(encryptUsername)).isEqualTo(username);
        assertThat(jasyptDecrypt(encryptPassword)).isEqualTo(password);
    }

    public void setup(String key) {
        encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
    }

    public String jasyptEncrypt(String str) {
        return encryptor.encrypt(str);
    }

    public String jasyptDecrypt(String str) {
        return encryptor.decrypt(str);
    }
}