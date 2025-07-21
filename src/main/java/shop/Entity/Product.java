package shop.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table
@Entity(name = "products")
public class Product extends  BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_bgn")
    private BigDecimal priceBgn;

    @Column(name = "price_euro")
    private BigDecimal priceEuro;

    @Column(name = "stock")
    private int stock;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceBgn() {
        return priceBgn;
    }

    public void setPriceBgn(BigDecimal priceBgn) {
        this.priceBgn = priceBgn;
    }

    public BigDecimal getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(BigDecimal priceEuro) {
        this.priceEuro = priceEuro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}