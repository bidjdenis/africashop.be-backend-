package africashop.be.services.Member;

import africashop.be.Repositories.CartItemsRepo;
import africashop.be.Repositories.ProductRepo;
import africashop.be.Repositories.UserRepo;
import africashop.be.dtos.CartItemsDto;
import africashop.be.dtos.ProductCartDto;
import africashop.be.entities.CartItems;
import africashop.be.entities.Product;
import africashop.be.entities.User;
import africashop.be.exceptions.ValidationException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartItemsRepo cartItemsRepo;


    @Override
    public ResponseEntity<?> addToCart(ProductCartDto productCartDto) {
        Optional<CartItems> existingCartItemOptional = cartItemsRepo.findByProductIdAndUserId(productCartDto.getProductId(),productCartDto.getUserId());
        if (existingCartItemOptional.isPresent()) {
            CartItems existingCartItem = existingCartItemOptional.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            CartItems updatedCartItem = cartItemsRepo.save(existingCartItem);
            return ResponseEntity.ok(updatedCartItem);
        } else {
            CartItems cartItems = new CartItems();
            Product product = productRepo.findById(productCartDto.getProductId()).orElseThrow(() -> new ValidationException("Product not found"));
            cartItems.setProduct(product);
            User user = userRepo.findById(productCartDto.getUserId()).orElseThrow(()-> new ValidationException("User not found"));
            cartItems.setUser(user);
            cartItems.setPrice(product.getPrice());
            cartItems.setQuantity(1L);
            CartItems savedCartItem = cartItemsRepo.save(cartItems);
            return ResponseEntity.ok(savedCartItem);
        }
    }


    @Override
    public List<CartItemsDto> getCart(Long userId) {
        List<CartItems> cartItems = cartItemsRepo.findByUserId(userId);
        List<CartItemsDto> cartItemsDtoList = new ArrayList<>();

        for(CartItems c : cartItems){
            CartItemsDto cartItemsDto = new CartItemsDto();
            cartItemsDto.setId(c.getId());
            cartItemsDto.setPrice(c.getPrice());
            cartItemsDto.setQuantity(c.getQuantity());
            cartItemsDto.setUserId(userId);
            cartItemsDto.setProductId(c.getProduct().getId());
            cartItemsDto.setProductName(c.getProduct().getName());
            cartItemsDto.setReturnedImg(c.getProduct().getImg());

            cartItemsDtoList.add(cartItemsDto);
        }
        return cartItemsDtoList ;
    }

    @Override
    public List<CartItemsDto> increaseProductQuantity(ProductCartDto productCartDto) {
        Optional<Product> optionalProduct = productRepo.findById(productCartDto.getProductId());
        Optional<CartItems> optionalCartItem = cartItemsRepo.findByProductIdAndUserId(
                productCartDto.getProductId(), productCartDto.getUserId()
        );
        List<CartItemsDto> cartItemsDtoList = new ArrayList<>();
        if(optionalProduct.isPresent() && optionalCartItem.isPresent()) {
            CartItems cartItem = optionalCartItem.get();
            Product product = optionalProduct.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemsRepo.save(cartItem);
            cartItemsDtoList.add(cartItem.getCartDto());

        }
        return null;
    }

    @Override
    public List<CartItemsDto> decreaseProductQuantity(ProductCartDto productCartDto) {
        Optional<Product> optionalProduct = productRepo.findById(productCartDto.getProductId());
        Optional<CartItems> optionalCartItem = cartItemsRepo.findByProductIdAndUserId(
                productCartDto.getProductId(), productCartDto.getUserId()
        );
        List<CartItemsDto> cartItemsDtoList = new ArrayList<>();
        if(optionalProduct.isPresent() && optionalCartItem.isPresent()) {
            CartItems cartItem = optionalCartItem.get();
            Product product = optionalProduct.get();
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemsRepo.save(cartItem);
            cartItemsDtoList.add(cartItem.getCartDto());
        }
        return null;
    }

    @Transactional
    @Override
    public void removeProductFromCart(Long productId) {
        cartItemsRepo.deleteByProductId(productId);
    }
}



