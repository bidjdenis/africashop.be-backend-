package africashop.be.dtos;

import lombok.Data;

@Data
public class CheckoutDto {

    private Long userId;

    private String address;

    private String city;
    private Long boite;
    private Long codePostale;
}
