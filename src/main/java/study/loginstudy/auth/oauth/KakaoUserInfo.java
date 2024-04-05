package study.loginstudy.auth.oauth;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;


    // 카카오는 id가 Long 타입으로 날라와서 이렇게 코드함
    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }
    @Override
    public String getProvider() {
        return "kakao";
    }

    // 카카오는 이메일, 닉네임은 map 타입으로 추출
    @Override
    public String getEmail() {
        return (String) ((Map) attributes.get("kakao_account")).get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map) attributes.get("properties")).get("nickname");
    }
}
