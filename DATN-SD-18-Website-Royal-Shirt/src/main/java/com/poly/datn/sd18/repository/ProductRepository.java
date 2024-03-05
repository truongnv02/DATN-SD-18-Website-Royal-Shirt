package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select c from Product c where c.name like %:name% and c.status = 0")
    List<Product> searchProductByName(@Param("name") String name);

    @Query(value = """
        SELECT
            new com.poly.datn.sd18.model.response.ProductResponse(
                p.id,
                p.name,
                COALESCE((SELECT MAX(i.urlImage) FROM Image i WHERE i.product.id = p.id), ''),
                p.description,
                MIN(pd.price) as minPrice,
                MAX(pd.price) as maxPrice,
                p.status
            )
        FROM
            Product p
        LEFT JOIN
            p.productDetails pd
        LEFT JOIN
            p.images i
        WHERE
            i.id = (SELECT MIN(id) FROM Image WHERE product.id = p.id)
        GROUP BY
            p.id, p.name, p.description, p.status
        ORDER BY
            p.id
        """)
    Page<ProductResponse> pageProductResponse(Pageable pageable);


}
