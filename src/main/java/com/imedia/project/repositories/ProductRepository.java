package com.imedia.project.repositories;

import com.imedia.project.entites.Category;
import com.imedia.project.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product , Long> {
    List<Product> findProductsByCategory(Category category);
}
