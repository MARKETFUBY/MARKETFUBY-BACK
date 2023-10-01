package MARKETFUBY.Member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex {
    MALE(0, "남자"),
    FEMALE(1, "여자"),
    NOTHING(2, "선택안함");

    private final int optionId;
    private final String option;
}
