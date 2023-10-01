package MARKETFUBY.Event.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="event")
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", updatable = false)
    private Long eventId;

    @Column(nullable = false)
    private String image;

    @Builder
    public Event(Long eventId, String image){
        this.eventId = eventId;
        this.image = image;
    }
}
