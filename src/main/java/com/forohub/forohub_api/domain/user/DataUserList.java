package com.forohub.forohub_api.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.forohub.forohub_api.domain.profile.Profile;

import java.util.Set;

public record DataUserList(
        String name,
        String status,
        @JsonIgnoreProperties("id")
        Set<Profile> profile
) {
    public DataUserList(UserEntity userEntity) {
        this(userEntity.getName(),
                Boolean.TRUE.equals(userEntity.getActive())?"Active":"Inactive",
                userEntity.getProfile()
        );
    }
}
