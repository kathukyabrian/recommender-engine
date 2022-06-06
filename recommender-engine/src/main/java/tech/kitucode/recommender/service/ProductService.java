package tech.kitucode.recommender.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.kitucode.recommender.domain.Product;
import tech.kitucode.recommender.exceptions.EntityNotFoundException;
import tech.kitucode.recommender.repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        log.info("Request to save product : {}", product.getName());

        product.setAddedOn(LocalDate.now());

        product = productRepository.save(product);

        return product;
    }

    public Product getOne(Long id) throws EntityNotFoundException {
        log.info("Request to get product with id : {}", id);

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new EntityNotFoundException("Product with the specified id does not exist");
        }
    }

    public List<Product> getMany(List<Long> ids) {
        log.info("Request to get products with ids : {}", ids);

        List<Product> products = new ArrayList<>();

        for (Long id : ids) {
            Product product = null;
            try {
                product = getOne(id);
            } catch (EntityNotFoundException e) {
                continue;
            }

            products.add(product);
        }

        return products;
    }
}
