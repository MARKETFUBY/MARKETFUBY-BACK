package MARKETFUBY.ReviewHelp.controller;

import MARKETFUBY.ReviewHelp.dto.ReviewHelpRequestDto;
import MARKETFUBY.ReviewHelp.service.ReviewHelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goods/reviews/{reviewId}/helps")
public class ReviewHelpController {
    private final ReviewHelpService reviewHelpService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createReviewHelp(@PathVariable Long reviewId, @RequestBody ReviewHelpRequestDto reviewHelpRequestDto){
        Long memberId = reviewHelpRequestDto.getMemberId();
        if(memberId != null){
            reviewHelpService.createReviewHelp(reviewId, memberId);
            return "도움돼요가 반영되었습니다.";
        }
        else{
            return "로그인이 필요한 서비스입니다.";
        }
    }

}
