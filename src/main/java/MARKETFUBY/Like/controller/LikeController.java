package MARKETFUBY.Like.controller;

import MARKETFUBY.Like.dto.LikeRequestDto;
import MARKETFUBY.Like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goods/{productId}/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createLike(@PathVariable Long productId, @RequestBody LikeRequestDto likeRequestDto){
        Long memberId = likeRequestDto.getMemberId();
        if(memberId != null){
            likeService.createLike(productId, memberId);
            return "찜한 상품에 추가되었습니다.";
        }
        else{
            return "로그인이 필요한 서비스입니다.";
        }
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteLike(@PathVariable Long productId, @RequestBody LikeRequestDto likeRequestDto){
        Long memberId = likeRequestDto.getMemberId();
        if(memberId != null){
            likeService.delete(productId, memberId);
            return "찜하기가 해제되었습니다.";
        }
        else{
            return "로그인이 필요한 서비스입니다.";
        }
    }
}
