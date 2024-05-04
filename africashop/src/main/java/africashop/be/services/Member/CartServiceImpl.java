package africashop.be.services.Member;

import africashop.be.Repositories.CartItemsRepo;
import africashop.be.Repositories.ProductRepo;
import africashop.be.Repositories.UserRepo;
import africashop.be.dtos.ProductCartDto;
import africashop.be.entities.CartItems;
import africashop.be.entities.Product;
import africashop.be.entities.User;
import africashop.be.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartItemsRepo cartItemsRepo;


    @Override
    public ResponseEntity<?> addToCart(ProductCartDto productCartDto) {
        CartItems cartItems = new CartItems();
        Product product = productRepo.findById(productCartDto.getProductId()).orElseThrow(() -> new ValidationException("Product not found"));
        cartItems.setProduct(product);
        User user = userRepo.findById(productCartDto.getUserId()).orElseThrow(()-> new ValidationException("user not found"));
        cartItems.setUser(user);
        cartItems.setId(cartItems.getId());
        cartItems.setPrice(product.getPrice());
        cartItems.setQuantity(1L);
        CartItems cart = cartItemsRepo.save(cartItems);
        return ResponseEntity.ok(cart);
    }
}
