package vantoan.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer amount;
    private String image;
    private Date dateTime;
    @Transient
    private MultipartFile imageMul;
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String name, Double price, Integer amount, Date dateTime, MultipartFile imageMul, Category category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.dateTime = dateTime;
        this.imageMul = imageMul;
        this.category = category;
    }

    public Product(String name, Double price, Integer amount, Date dateTime, MultipartFile imageMul) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.dateTime = dateTime;
        this.imageMul = imageMul;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public MultipartFile getImageMul() {
        return imageMul;
    }

    public void setImageMul(MultipartFile imageMul) {
        this.imageMul = imageMul;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
