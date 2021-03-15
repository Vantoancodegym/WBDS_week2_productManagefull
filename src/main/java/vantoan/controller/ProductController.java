package vantoan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vantoan.model.Category;
import vantoan.model.Product;
import vantoan.service.ICategoryService;
import vantoan.service.IProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private Environment environment;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;
    @ModelAttribute("categories")
    public List<Category> getCategories(){
        return iCategoryService.findAll();
    }
    @GetMapping("")
    public ModelAndView showAll(@PageableDefault(size = 3) Pageable pageable){
        Page<Product> productList=iProductService.findAll(pageable);
        return new ModelAndView("home","productList",productList);
    }
    @GetMapping("create")
    public ModelAndView showFormCreate(){
        System.out.println(getCategories());
        return new ModelAndView("create","product",new Product());
    }
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute Product product) throws IOException{
        MultipartFile imageMul= product.getImageMul();
        String image= imageMul.getOriginalFilename();
        String resource=environment.getProperty("file_upload").toString();
        FileCopyUtils.copy(imageMul.getBytes(), new File(resource+image));
        product.setImage(image);
        iProductService.save(product);
        return new ModelAndView("redirect:/products");
    }
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam Long id){
        iProductService.delete(id);
        return new ModelAndView("redirect:/products");
    }
    @GetMapping("edit")
    public ModelAndView showEditForm(@RequestParam Long id){
        Product product= iProductService.findById(id);
        return new ModelAndView("edit","product",product);
    }
    @PostMapping("edit")
    public ModelAndView edit(@RequestParam Long id, @ModelAttribute Product product){
        product.setId(id);
        iProductService.save(product);
        return new ModelAndView("redirect:/products");
    }
    @PostMapping("search")
    public ModelAndView search(@RequestParam Long category_id, @PageableDefault(size = 3) Pageable pageable){
        Page<Product> productList= iProductService.findByCategory(category_id, pageable);
        return new ModelAndView("search","productList",productList);

    }
    @GetMapping("top_expensive")
    public ModelAndView top_expensive(){
        List<Product> productList= iProductService.findTopExpensive(5);
        return new ModelAndView("search","productList",productList);

    }
    @GetMapping("top_new")
    public ModelAndView top_new(){
        List<Product> productList= iProductService.findTopNew(5);
        return new ModelAndView("search","productList",productList);

    }
    @GetMapping("get_sum")
    public ModelAndView get_sum( @PageableDefault(size = 3) Pageable pageable){
        Double sum= iProductService.getSum();
        ModelAndView modelAndView=new ModelAndView("home","sum",sum);
        Page<Product> productList=iProductService.findAll(pageable);
        modelAndView.addObject("productList",productList);
        return modelAndView;
    }



}
