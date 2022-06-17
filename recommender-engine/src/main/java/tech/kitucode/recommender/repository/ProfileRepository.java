package tech.kitucode.recommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.recommender.domain.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findOneByGlobalUserId(Long globalUserId);
}
