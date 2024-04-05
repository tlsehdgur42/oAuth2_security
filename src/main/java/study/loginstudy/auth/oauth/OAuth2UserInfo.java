package study.loginstudy.auth.oauth;

public interface OAuth2UserInfo {
    // 제공자ID
    String getProviderId();
    // 제공자
    String getProvider();
    // 이메일
    String getEmail();
    // 닉네임
    String getName();
}
