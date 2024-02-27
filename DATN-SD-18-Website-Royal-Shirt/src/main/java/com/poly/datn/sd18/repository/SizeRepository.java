package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[status]\n" +
            "      ,[shirt_length]\n" +
            "      ,[shirt_width]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[sizes]\n" +
            "WHERE [status] = 0",nativeQuery = true)
    List<Size> getAllActive();

    List<Size> findByName(String name);
}
