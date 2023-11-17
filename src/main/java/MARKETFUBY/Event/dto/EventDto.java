package MARKETFUBY.Event.dto;

import MARKETFUBY.Event.domain.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventDto{
        private Long eventId;
        private String image;

    public static EventDto from(Event event) {
        return EventDto.builder()
                .eventId(event.getEventId())
                .image(event.getImage())
                .build();
    }
}
