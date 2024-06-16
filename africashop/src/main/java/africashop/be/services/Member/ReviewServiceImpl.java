package africashop.be.services.Member;

import africashop.be.Repositories.ProductRepo;
import africashop.be.Repositories.ReviewRepo;
import africashop.be.Repositories.UserRepo;
import africashop.be.dtos.ReviewDto;
import africashop.be.entities.Product;
import africashop.be.entities.Review;
import africashop.be.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepo reviewRepo;
    private final ProductRepo productRepo;

    private final UserRepo userRepo;
    @Override
    public ReviewDto sendReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepo.findById(reviewDto.getProductId());
        Optional<User> userOptional = userRepo.findById(reviewDto.getUserId());

        if(optionalProduct.isPresent() && userOptional.isPresent()){
            Review review = new Review();
            review.setDescription(reviewDto.getDescription());
            review.setRating(reviewDto.getRating());
            review.setUser(userOptional.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getImg().getBytes());
            return reviewRepo.save(review).getDto();

        }
        return null;
    }
}
