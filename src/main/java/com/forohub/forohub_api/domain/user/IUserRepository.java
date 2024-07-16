package com.forohub.forohub_api.domain.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByEmail(String email);
    Boolean existsByEmail(String email);
}
