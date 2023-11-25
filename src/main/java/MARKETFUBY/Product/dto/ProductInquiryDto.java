package MARKETFUBY.Product.dto;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Inquiry.domain.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductInquiryDto {
    private String title;
    private Boolean isSecret;
    private String name;
    private LocalDate date;
    private Status status;

    public static ProductInquiryDto from(Inquiry inquiry){
        return ProductInquiryDto.builder()
                .title(inquiry.getWriter().getName())
                .isSecret(inquiry.getIsSecret())
                .name(inquiry.getWriter().getName())
                .date(inquiry.getCreatedAt().toLocalDate())
                .status(inquiry.getStatus())
                .build();
    }
}
