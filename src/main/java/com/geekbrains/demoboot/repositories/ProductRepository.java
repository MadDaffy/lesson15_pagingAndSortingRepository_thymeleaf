package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository /*extends JpaRepository<Product, Long>*/ extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {//

    List<Product> findAllByTitleContaining(String title);

   List<Product> findFirst3ByOrderByViewDesc();

}
