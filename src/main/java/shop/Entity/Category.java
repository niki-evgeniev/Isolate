package shop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table
@Entity(name = "categories")
public class Category extends BaseEntity {

    private String name;
}
