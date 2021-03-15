package vantoan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vantoan.model.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category,Long> {
}
