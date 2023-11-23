package MARKETFUBY.Cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MARKETFUBY.Cart.domain.Cart;
import MARKETFUBY.Cart.dto.CartDto;
import MARKETFUBY.Cart.dto.DeleteCartDto;
import MARKETFUBY.Cart.dto.PostCartDto;
import MARKETFUBY.Cart.dto.UpdateCartDto;
import MARKETFUBY.Cart.repository.CartRepository;
import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.CartProduct.repository.CartProductRepository;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

	private final CartRepository cartRepository;
	private final CartProductRepository cartProductRepository;
	private final ProductRepository productRepository;
	private final MemberService memberService;

	@Transactional(readOnly=true)
	public CartDto getCartInfo(){

		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();

		List<CartDto.SingleProduct> roomTempList=new ArrayList<>();
		List<CartDto.SingleProduct> refrigeList=new ArrayList<>();
		List<CartDto.SingleProduct> frozenList=new ArrayList<>();
		int totalAmount=0, discountAmount=0, paymentAmount=0;

		//장바구니에 담은 물품 하나도 없는 경우
		if(cartRepository.countByMember(member)==0){
			CartDto cartDto= CartDto.builder()
				.roomTempList(roomTempList)
				.refrigeList(refrigeList)
				.frozenList(frozenList)
				.totalAmount(totalAmount)
				.discountAmount(discountAmount)
				.paymentAmount(paymentAmount)
				.build();
			return cartDto;
		}
		//장바구니 정보 있는 경우
		else{
			//findBymemberId중에 cartId가장 큰 애? 즉 제일 최신
			Cart cart=cartRepository.findTopByMemberOrderByCartIdDesc(member).orElseThrow(()->new IllegalArgumentException("해당 id에 해당하는 member가 없습니다. id="+member.getMemberId()));
			Long cartId=cart.getCartId();
			//cartId로 Cart product들 불러오고
			List<CartProduct> cartProductList=cartProductRepository.findAllByCart(cart);
			//productId로 정보불러오고 냉장인지.냉동인지.상온인지 리스트 작성
			for(CartProduct cartProduct : cartProductList){
				String type= String.valueOf(cartProduct.getProduct().getPacking());
				System.out.println("productType="+type);
				if(type=="FREEZE"){
					frozenList.add(CartDto.SingleProduct.of(cartProduct));
				} else if (type=="COLD") {
					refrigeList.add(CartDto.SingleProduct.of(cartProduct));
				} else{
					roomTempList.add(CartDto.SingleProduct.of(cartProduct));
				}
				totalAmount+=cartProduct.getProduct().getPrice()*cartProduct.getCount();
				discountAmount+=cartProduct.getProduct().getPrice()*cartProduct.getProduct().getDiscount()/100*cartProduct.getCount();
			}
			paymentAmount=totalAmount-discountAmount;

			CartDto cartDto= CartDto.builder()
				.roomTempList(roomTempList)
				.refrigeList(refrigeList)
				.frozenList(frozenList)
				.totalAmount(totalAmount)
				.discountAmount(discountAmount)
				.paymentAmount(paymentAmount)
				.build();

			return cartDto;
		}
	}

	public void postCart(PostCartDto postCartDto){

		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();

		Cart cart=new Cart();
		if(cartRepository.countByMember(member)==0){
			cart=Cart.builder()
				.member(member)
				.build();
			cartRepository.save(cart);
		} else{
			cart=cartRepository.findTopByMemberOrderByCartIdDesc(member)
				.orElseThrow(()-> new IllegalArgumentException("장바구니가 존재하지 않습니다."));
		}

		Long productId= postCartDto.getProductId();
		Product product=productRepository.findById(productId)
			.orElseThrow(()-> new IllegalArgumentException("제품이 존재하지 않습니다."));

		//장바구니에 해당 제품이 이미 있다면 원래 개수에 더 추가
		if(cartProductRepository.existsByCartAndProduct(cart, product)){
			CartProduct cartProduct=cartProductRepository.findByProductAndCart(product,cart)
				.orElseThrow(()-> new IllegalArgumentException("장바구니 제품이 존재하지 않습니다."));
			cartProduct.updateCartProduct(cartProduct.getCount()+ postCartDto.getCount());
			cartProductRepository.save(cartProduct);
		}
		//처음 장바구니에 넣는 제품이면 새로 그냥 추가
		else{
			cartProductRepository.save(postCartDto.toEntity(cart,product,postCartDto));
		}
	}

	public void updateCart(UpdateCartDto updateCartDto){
		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();

		Cart cart=cartRepository.findTopByMemberOrderByCartIdDesc(member)
			.orElseThrow(()-> new IllegalArgumentException("장바구니가 존재하지 않습니다."));
		Long cartProductId= updateCartDto.getCartProductId();
		CartProduct cartProduct=cartProductRepository.findById(cartProductId)
			.orElseThrow(()-> new IllegalArgumentException("장바구니 제품이 존재하지 않습니다."));
		cartProduct.updateCartProduct(updateCartDto.getCount());
		cartProductRepository.save(cartProduct);
	}

	public void deleteCart(DeleteCartDto deleteCartDto){
		CartProduct cartProduct=cartProductRepository.findById(deleteCartDto.getCartProductId()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 장바구니 제품입니다."));
		cartProductRepository.delete(cartProduct);
	}
}
