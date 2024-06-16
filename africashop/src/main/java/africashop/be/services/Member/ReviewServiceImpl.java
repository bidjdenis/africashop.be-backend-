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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<ReviewDto> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepo.findByProductId(productId);
        return reviews.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDto convertToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setDescription(review.getDescription());
        dto.setReturnedImg(review.getImg());
        dto.setProductId(review.getProduct().getId());
        dto.setUserId(review.getUser().getId());
        dto.setUsername(review.getUser().getName());
        dto.setProductName(review.getProduct().getName());
        return dto;
    }
}
