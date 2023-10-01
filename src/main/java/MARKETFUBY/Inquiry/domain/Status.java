package MARKETFUBY.Inquiry.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    WAIT(0, "답변대기"),
    DONE(1, "답변완료");

    private final int statusId;
    private final String content;
}
