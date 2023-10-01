package MARKETFUBY.Product.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Packing {
    COLD(0, "냉장"),
    FREEZE(1, "냉동"),
    NORMAL(2, "상온");

    private final int optionId;
    private final String option;
}
