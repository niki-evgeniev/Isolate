package shop.DTO.Porduct;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ListAllProductDTO {

    private long id;
    private String name;
    private String model;
    private String url;
    private String description;
    private BigDecimal priceBgn;
    private BigDecimal priceEuro;
    private String imageFile1;
    private String imageFile2;
    private String imageFile3;
    private String imageFile4;

    public ListAllProductDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getImageFile1() {
        return imageFile1;
    }

    public void setImageFile1(String imageFile1) {
        this.imageFile1 = imageFile1;
    }

    public String getImageFile2() {
        return imageFile2;
    }

    public void setImageFile2(String imageFile2) {
        this.imageFile2 = imageFile2;
    }

    public String getImageFile3() {
        return imageFile3;
    }

    public void setImageFile3(String imageFile3) {
        this.imageFile3 = imageFile3;
    }

    public String getImageFile4() {
        return imageFile4;
    }

    public void setImageFile4(String imageFile4) {
        this.imageFile4 = imageFile4;
    }
}
