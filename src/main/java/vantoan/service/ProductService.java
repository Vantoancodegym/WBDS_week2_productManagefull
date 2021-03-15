package vantoan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vantoan.model.Product;
import vantoan.repository.ProductRepo;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    ProductRepo productRepo;
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findOne(id);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Page<Product> findByCategory(Long category_id, Pageable pageable) {
        return productRepo.findProByCategory(pageable, category_id);
    }

    @Override
    public void delete(Long id) {
        productRepo.delete(id);

    }

    @Override
    public List<Product> findTopExpensive(int index) {
        return productRepo.findTopExpensive(index);
    }

    @Override
    public List<Product> findTopNew(int index) {
        return productRepo.findTopNew(index);
    }

    @Override
    public Double getSum() {
        return productRepo.getSum();
    }
}
