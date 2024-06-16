package africashop.be.services.Member;

import africashop.be.dtos.ReviewDto;
import africashop.be.entities.Review;

import java.io.IOException;
import java.util.List;

public interface ReviewService {

    ReviewDto sendReview(ReviewDto reviewDto) throws IOException;
    List<ReviewDto> getReviewsByProductId(Long productId);

    ReviewDto convertToDto(Review review);

}
