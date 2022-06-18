package tech.kitucode.recommender.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.kitucode.recommender.domain.Product;
import tech.kitucode.recommender.domain.Profile;
import tech.kitucode.recommender.service.ProductService;
import tech.kitucode.recommender.service.dto.ProfileDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProfileMapper {

    private final ProductService productService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProfileMapper(ProductService productService) {
        this.productService = productService;
    }

    public ProfileDTO toDTO(Profile profile) {

        log.info("Mapping entity {} to dto", profile);

        if (profile == null) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setGlobalUserId(profile.getGlobalUserId());
        profileDTO.setUpdatedOn(profile.getUpdatedOn());


        List<Integer> productList = null;
        try {
            productList = objectMapper.readValue(profile.getRecommendations(), List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<Product> products = productService.getMany(productList);
        profileDTO.setRecommendations(products);

        return profileDTO;
    }
}
