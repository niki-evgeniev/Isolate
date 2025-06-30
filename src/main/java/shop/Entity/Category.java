package shop.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "category_name")
    private String name;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
