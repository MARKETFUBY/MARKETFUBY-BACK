package MARKETFUBY.Event.service;

import MARKETFUBY.Event.domain.Event;
import MARKETFUBY.Event.dto.EventDto;
import MARKETFUBY.Event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    // 이벤트 목록 조회
    public List<EventDto> getEventList() {
        List<Event> eventList = new Stack<>();
        eventList = eventRepository.findAll();
        List<EventDto> eventDtoList = eventList.stream()
                .map(h -> EventDto.builder()
                        .event(h)
                        .build())
                .collect(Collectors.toList());
        return eventDtoList;
    }
}
