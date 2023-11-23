package MARKETFUBY.Event.dto;

import MARKETFUBY.Event.domain.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventDto{
    private Long eventId;
    private String image;

    @Builder
    public EventDto(Event event){
        this.eventId = event.getEventId();
        this.image = event.getImage();
    }
}
