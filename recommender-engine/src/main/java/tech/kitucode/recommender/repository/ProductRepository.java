package tech.kitucode.recommender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.recommender.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
