package africashop.be.services.Member;

import africashop.be.dtos.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    ReviewDto sendReview(ReviewDto reviewDto) throws IOException;

}
