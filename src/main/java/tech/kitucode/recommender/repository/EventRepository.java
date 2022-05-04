package tech.kitucode.recommender.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.recommender.domain.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByGlobalUserId(Long globalUserId, Pageable pageable);
    Page<Event> findAllByApplicationId(Long applicationId, Pageable pageable);
}
