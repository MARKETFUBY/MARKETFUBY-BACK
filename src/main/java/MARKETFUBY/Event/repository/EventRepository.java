package MARKETFUBY.Event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import MARKETFUBY.Event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
