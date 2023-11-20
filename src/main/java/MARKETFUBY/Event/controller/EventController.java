package MARKETFUBY.Event.controller;

import MARKETFUBY.Event.dto.EventDto;
import MARKETFUBY.Event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/market-benefit")
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventDto> getEventList() {
        List<EventDto> eventDtoList = eventService.getEventList();
        return eventDtoList;
    }
}
