package MARKETFUBY.Like.service;

import MARKETFUBY.Like.domain.Like;
import MARKETFUBY.Like.repository.LikeRepository;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final MemberService memberService;
    private final LikeRepository likeRepository;

    @Lazy
    @Autowired
    private ProductService productService;

    public void createLike(Long productId, Long memberId){
        Product product = productService.findProductById(productId);
        Member member = memberService.findMemberById(memberId);

        if(isExistsByMemberAndProduct(member, product)){
            throw new RuntimeException("이미 찜한 상품입니다.");
        }

        Like like = Like.builder()
                .product(product)
                .member(member)
                .build();

        likeRepository.save(like);
    }

    public void delete(Long productId, Long memberId){
        Product product = productService.findProductById(productId);
        Member member = memberService.findMemberById(memberId);

        Like like = likeRepository.findByMemberAndProduct(member, product)
                .orElseThrow(() -> new RuntimeException("찜하기가 존재하지 않습니다."));
        likeRepository.delete(like);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByMemberAndProduct(Member member, Product product){
        return likeRepository.existsByMemberAndProduct(member, product);
    }
}
