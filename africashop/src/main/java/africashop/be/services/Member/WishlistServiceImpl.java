package africashop.be.services.Member;

import africashop.be.Repositories.ProductRepo;
import africashop.be.Repositories.UserRepo;
import africashop.be.Repositories.WishlistRepo;
import africashop.be.dtos.ProductCartDto;
import africashop.be.dtos.WishlistDto;
import africashop.be.entities.Product;
import africashop.be.entities.User;
import africashop.be.entities.Wishlist;
import africashop.be.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepo wishlistRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    @Override
    public ResponseEntity<?> addToWishList(ProductCartDto productCartDto) {
        Optional<Wishlist> existingWishlistOptional = wishlistRepo.findByProductIdAndUserId(productCartDto.getProductId(),productCartDto.getUserId());
        if (existingWishlistOptional.isPresent()) {
            Wishlist existingWishlist = existingWishlistOptional.get();
            return ResponseEntity.ok(wishlistRepo.save(existingWishlist));
        } else {
            Wishlist wishlist = new Wishlist();
            Product product = productRepo.findById(productCartDto.getProductId()).orElseThrow(() -> new ValidationException("Product not found"));
            User user = userRepo.findById(productCartDto.getUserId()).orElseThrow(()-> new ValidationException("User not found"));
            wishlist.setUser(user);
            wishlist.setProduct(product);
            Wishlist savedWishListItem = wishlistRepo.save(wishlist);
            return ResponseEntity.ok(savedWishListItem);
        }
    }

    @Override
    public List<WishlistDto> getWishListByUserId(Long userId) {
        List<Wishlist> wishLists = wishlistRepo.findByUserId(userId);
        List<WishlistDto> wishListDtos = new ArrayList<>();

        for(Wishlist w : wishLists){
            WishlistDto wishListDto = new WishlistDto();
            wishListDto.setId(w.getId());
            wishListDto.setUserId(userId);
            wishListDto.setProductId(w.getProduct().getId());
            wishListDto.setProductName(w.getProduct().getName());
            wishListDto.setPrice(w.getProduct().getPrice());
            wishListDto.setReturnedImg(w.getProduct().getImg());
            wishListDto.setCategoryName(w.getProduct().getCategory().getName());
            wishListDtos.add(wishListDto);
        }
        return wishListDtos;
    }
}
