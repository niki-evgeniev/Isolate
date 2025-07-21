package shop.DTO.Porduct;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class AddProductDTO {

    private String name;
    private String model;
    private String description;
    private BigDecimal price;
    private String category;
    private MultipartFile imageFile1;
    private MultipartFile imageFile2;
    private MultipartFile imageFile3;
    private MultipartFile imageFile4;

    public AddProductDTO() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImageFile1() {
        return imageFile1;
    }

    public void setImageFile1(MultipartFile imageFile1) {
        this.imageFile1 = imageFile1;
    }

    public MultipartFile getImageFile2() {
        return imageFile2;
    }

    public void setImageFile2(MultipartFile imageFile2) {
        this.imageFile2 = imageFile2;
    }

    public MultipartFile getImageFile3() {
        return imageFile3;
    }

    public void setImageFile3(MultipartFile imageFile3) {
        this.imageFile3 = imageFile3;
    }

    public MultipartFile getImageFile4() {
        return imageFile4;
    }

    public void setImageFile4(MultipartFile imageFile4) {
        this.imageFile4 = imageFile4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
