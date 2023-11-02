package MARKETFUBY.Cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MARKETFUBY.Cart.domain.Cart;
import MARKETFUBY.Cart.dto.CartDto;
import MARKETFUBY.Cart.dto.PostCartDto;
import MARKETFUBY.Cart.repository.CartRepository;
import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.CartProduct.repository.CartProductRepository;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

	private final CartRepository cartRepository;
	private final CartProductRepository cartProductRepository;
	private final ProductRepository productRepository;

	@Transactional(readOnly=true)
	public CartDto getCartInfo(){
		//현재 로그인 중인 사용자 불러오는 단계 필요
		Long memberId=1L;
		Member member=new Member();
		member.setMemberId(1L);
		//findBymemberId중에 cartId가장 큰 애? 즉 제일 최신
		Cart cart=cartRepository.findTopByMemberOrderByCartIdDesc(member).orElseThrow(()->new IllegalArgumentException("해당 id에 해당하는 member가 없습니다. id="+memberId));
		Long cartId=cart.getCartId();
		//cartId로 Cart product들 불러오고
		List<CartProduct> cartProductList=cartProductRepository.findAllByCart(cart);
		//productId로 정보불러오고 냉장인지.냉동인지.상온인지 리스트 작성
		List<CartDto.SingleProduct> frozenList=new ArrayList<>();
		List<CartDto.SingleProduct> refrigeList=new ArrayList<>();
		List<CartDto.SingleProduct> roomTempList=new ArrayList<>();
		CartDto cartDto=new CartDto();
		System.out.println("cartId="+cartId);
		int totalAmount=0, discountAmount=0, paymentAmount=0;
		for(CartProduct cartProduct : cartProductList){
			String type= String.valueOf(cartProduct.getProduct().getPacking());
			System.out.println("productType="+type);
			if(type=="FREEZE"){
				frozenList.add(CartDto.SingleProduct.of(cartProduct));
			} else if (type=="COLD") {
				refrigeList.add(CartDto.SingleProduct.of(cartProduct));
			} else{
				roomTempList.add(CartDto.SingleProduct.of(cartProduct));
				//cartDto.getRoomTempList().add(CartDto.SingleProduct.of(cartProduct));
			}
			totalAmount+=cartProduct.getProduct().getPrice()*cartProduct.getCount();
			discountAmount+=cartProduct.getProduct().getPrice()*cartProduct.getProduct().getDiscount()/100*cartProduct.getCount();
		}
		paymentAmount=totalAmount-discountAmount;
		cartDto.setFrozenList(frozenList);
		cartDto.setRefrigeList(refrigeList);
		cartDto.setRoomTempList(roomTempList);
		//totalprice, discount, paymenttotal계산해서 dto에 넣어주기
		//배송지 받아오기 일단 임의
		cartDto.setAddress("경기 안산시 상록구");
		cartDto.setTotalAmount(totalAmount);
		cartDto.setDiscountAmount(discountAmount);
		cartDto.setPaymentAmount(paymentAmount);

		return cartDto;
	}

	public void postCart(PostCartDto postCartDto){
		//현재 로그인 중인 사용자 불러오는 단계 필요
		Long memberId=1L;
		Member member=new Member();
		member.setMemberId(1L);
		//Cart tempcart=new Cart(member);
		//cartRepository.save(tempcart);

		Cart cart=cartRepository.findTopByMemberOrderByCartIdDesc(member)
			.orElseThrow(()-> new IllegalArgumentException("장바구니가 존재하지 않습니다."));
		Long cartId=cart.getCartId();
		Long productId= postCartDto.getProductId();;
		Product product=productRepository.findById(productId)
			.orElseThrow(()-> new IllegalArgumentException("제품이 존재하지 않습니다."));
		cartProductRepository.save(postCartDto.toEntity(cart,product,postCartDto));
	}

	public void updateCart(PostCartDto postCartDto){
		//현재 로그인 중인 사용자 불러오는 단계 필요
		Long memberId=1L;
		Member member=new Member();
		member.setMemberId(1L);
		//Cart tempcart=new Cart(member);
		//cartRepository.save(tempcart);

		Cart cart=cartRepository.findTopByMemberOrderByCartIdDesc(member)
			.orElseThrow(()-> new IllegalArgumentException("장바구니가 존재하지 않습니다."));
		//Long cartId=cart.getCartId();
		Long productId= postCartDto.getProductId();;
		Product product=productRepository.findById(productId)
			.orElseThrow(()-> new IllegalArgumentException("제품이 존재하지 않습니다."));
		CartProduct cartProduct=cartProductRepository.findByProductAndCart(product, cart)
			.orElseThrow(()-> new IllegalArgumentException("장바구니 제품이 존재하지 않습니다."));
		cartProduct.updateCartProduct(postCartDto.getCount());
		cartProductRepository.save(cartProduct);
	}
}
