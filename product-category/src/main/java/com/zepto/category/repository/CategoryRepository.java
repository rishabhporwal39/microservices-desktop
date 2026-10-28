package com.zepto.category.repository;

import com.zepto.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c LEFT JOIN FETCH c.products")
    //@EntityGraph(attributePaths = { "products" })
    public List<CategoryEntity> getAllCategories();


}
