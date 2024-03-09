package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.dto.response.ProductDetailCounterResponse;
import com.poly.datn.sd18.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounterRepository extends JpaRepository<ProductDetail, Integer> {
    @Query(value = "WITH SizesOrder AS (\n" +
            "    SELECT\n" +
            "        [id],\n" +
            "        [name],\n" +
            "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS SizeOrder\n" +
            "    FROM \n" +
            "        [dbo].[sizes]\n" +
            "),\n" +
            "ImagesOrder AS (\n" +
            "    SELECT\n" +
            "        [product_id],\n" +
            "        [url_image],\n" +
            "        ROW_NUMBER() OVER (PARTITION BY [product_id] ORDER BY (SELECT NULL)) AS ImageOrder\n" +
            "    FROM\n" +
            "        [dbo].[images]\n" +
            ")\n" +
            "\n" +
            "SELECT \n" +
            "    dbo.product_details.id AS id,\n" +
            "    dbo.products.name AS name,\n" +
            "    ImagesOrder.url_image as image,\n" +
            "    dbo.colors.name AS colorName,\n" +
            "    dbo.sizes.name AS sizeName,\n" +
            "    dbo.product_details.quantity AS quantity,\n" +
            "    dbo.product_details.price AS price,\n" +
            "    COALESCE(dbo.discounts.discount, 0) AS discount\n" +
            "FROM   \n" +
            "    dbo.product_details\n" +
            "    INNER JOIN dbo.colors ON dbo.product_details.color_id = dbo.colors.id\n" +
            "    INNER JOIN dbo.sizes ON dbo.product_details.size_id = dbo.sizes.id\n" +
            "    INNER JOIN dbo.products ON dbo.products.id = dbo.product_details.product_id\n" +
            "    INNER JOIN ImagesOrder ON dbo.products.id = ImagesOrder.product_id AND ImagesOrder.ImageOrder = 1\n" +
            "    LEFT JOIN dbo.discounts ON dbo.products.discount_id = dbo.discounts.id\n" +
            "    INNER JOIN SizesOrder ON dbo.sizes.id = SizesOrder.id\n" +
            "GROUP BY \n" +
            "    dbo.product_details.id,\n" +
            "    dbo.products.name,\n" +
            "    ImagesOrder.url_image,\n" +
            "    dbo.colors.name,\n" +
            "    dbo.sizes.name,\n" +
            "    dbo.product_details.quantity,\n" +
            "    dbo.product_details.price,\n" +
            "    COALESCE(dbo.discounts.discount, 0),\n" +
            "    SizesOrder.SizeOrder\n" +
            "ORDER BY \n" +
            "\tdbo.products.name,\n" +
            "    dbo.colors.name,\n" +
            "    SizesOrder.SizeOrder;\n", nativeQuery = true)
    List<ProductDetailCounterResponse> getListProductDetailCounter();

    @Query(value = "UPDATE [dbo].[product_details]\n" +
            "   SET [quantity] = [quantity] - :quantity\n" +
            " WHERE [id] = :idProductDetail",nativeQuery = true)
    ProductDetail updateQuantity(@Param("quantity") Integer quantity,@Param("idProductDetail") Integer idProductDetail);
}
