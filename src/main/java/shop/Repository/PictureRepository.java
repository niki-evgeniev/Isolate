package shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.Entity.Picture;

import java.util.List;
import java.util.UUID;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findAllByProductId(Long id);
}
