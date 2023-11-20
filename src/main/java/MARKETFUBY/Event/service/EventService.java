package MARKETFUBY.Event.service;

import MARKETFUBY.Event.domain.Event;
import MARKETFUBY.Event.dto.EventDto;
import MARKETFUBY.Event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    // 이벤트 목록 조회
    @Transactional(readOnly = true)
    public List<EventDto> getEventList() {
        List<Event> allEvents = eventRepository.findAll();

        List<EventDto> eventDtoList = new ArrayList<>();
        for (Event event : allEvents) {
            Long eventId = event.getEventId();
            String image = event.getImage();
        }
        return eventDtoList;
    }
}
