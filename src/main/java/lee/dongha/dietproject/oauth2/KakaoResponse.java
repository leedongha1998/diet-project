package lee.dongha.dietproject.oauth2;

import java.util.Map;
import java.util.Objects;

public class KakaoResponse implements OAuth2Response{
    private final Map<String, Object> attribute;
    private final Map<String, Object> properties;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("kakao_account");
        this.properties = (Map<String, Object>) attribute.get("properties");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return null;
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return properties.get("nickname").toString();
    }

    public String getGender(){
        return attribute.get("gender").toString();
    }

    public String getBirthday(){
        return attribute.get("birthday").toString();
    }
}
