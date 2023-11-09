package MARKETFUBY.Product.service;

import MARKETFUBY.Like.domain.Like;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product findProductById(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(()->new EntityNotFoundException("해당 상품이 존재하지 않습니다."));
    }
}
