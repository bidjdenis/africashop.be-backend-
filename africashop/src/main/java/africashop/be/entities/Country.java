package africashop.be.entities;

import africashop.be.dtos.CountryDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;


    public CountryDto getDto() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(id);
        countryDto.setName(name);
        countryDto.setByteImg(img);
        return countryDto;
    }
}
