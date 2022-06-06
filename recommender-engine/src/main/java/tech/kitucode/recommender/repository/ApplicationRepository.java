package tech.kitucode.recommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.recommender.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
