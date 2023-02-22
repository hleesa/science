package com.ftence.backend.oauth2.dto;

import com.ftence.backend.entity.Role;
import com.ftence.backend.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String intraId;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String intraId, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.intraId = intraId;
        this.picture = picture;
    }
    public static OAuthAttributes of(String userNameAttributeName, Map<String, Object> attributes) {
        return ofSeoul42(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofSeoul42(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .intraId((String) attributes.get("login"))
                .picture((String) attributes.get("image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .intraId(intraId)
                .picture(picture)
                .role(Role.USER)
                .build();
    }
}
