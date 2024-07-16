package com.forohub.forohub_api.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByName(String name);
}
