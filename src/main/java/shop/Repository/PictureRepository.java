package shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.Entity.Picture;

import java.util.UUID;

public interface PictureRepository extends JpaRepository<Picture, UUID> {

}
