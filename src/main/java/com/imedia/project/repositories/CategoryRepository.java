package com.imedia.project.repositories;

import com.imedia.project.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryByName(String name);
}
