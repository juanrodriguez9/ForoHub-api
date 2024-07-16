package com.forohub.forohub_api.domain.user;

import jakarta.validation.constraints.Email;

public record DataAuthenticationUser(
        @Email String email,
        String password
) {
}
