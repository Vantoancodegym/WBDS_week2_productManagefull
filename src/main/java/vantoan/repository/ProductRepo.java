package vantoan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vantoan.model.Product;

import java.util.List;
@Repository
public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {
    @Override
    Page<Product> findAll(Pageable pageable);
//    @Query (value = "select p from Product order by price desc l")
    @Query (value = "select * from product order by price desc limit ?",nativeQuery = true)
    List<Product> findTopExpensive(int index);
    @Query (value = "select * from product order by dateTime desc limit ?",nativeQuery = true)
    List<Product> findTopNew(int index);
    @Query(value = "select sum(price*amount) from product ",nativeQuery = true)
    Double getSum();
    @Query(value = "select p from Product as p where p.category.id=:id")
    Page<Product> findProByCategory(Pageable pageable, @Param("id") Long category_id);

}
