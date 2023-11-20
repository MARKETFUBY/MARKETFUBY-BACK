package MARKETFUBY.Member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UseAgreement {
    SMS(0, "SMS"),
    MAIL(1, "메일"),
    BOTH(2, "둘다");

    private final int optionId;
    private final String option;
}
