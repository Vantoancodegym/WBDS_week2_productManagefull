package vantoan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vantoan.model.Category;
import vantoan.repository.CategoryRepo;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findOne(id);
    }
}
