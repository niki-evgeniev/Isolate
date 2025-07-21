package shop.Service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.DTO.Porduct.AddProductDTO;
import shop.Entity.Picture;
import shop.Entity.Product;
import shop.Repository.PictureRepository;
import shop.Repository.ProductRepository;
import shop.Service.ProductService;
import shop.Util.ConvertorBgToEn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final static String IMAGE_PATH = "./imagesApp/products/";
    private final ProductRepository productRepository;
    private final PictureRepository pictureRepository;
    private final ConvertorBgToEn convertorBgToEn;

    public ProductServiceImpl(ProductRepository productRepository, PictureRepository pictureRepository,
                              ConvertorBgToEn convertorBgToEn) {
        this.productRepository = productRepository;
        this.pictureRepository = pictureRepository;
        this.convertorBgToEn = convertorBgToEn;
    }

    @Override
    public void addProduct(AddProductDTO addProductDTO) {

        List<MultipartFile> imageFiles = List.of(
                addProductDTO.getImageFile1(), addProductDTO.getImageFile2(),
                addProductDTO.getImageFile3(), addProductDTO.getImageFile4());
        String englishName = convertorBgToEn.convertCyrillicToLatin(addProductDTO.getName());
        Product product = new Product();
        product.setName(addProductDTO.getName());
        product.setModel(addProductDTO.getModel());
        product.setDescription(addProductDTO.getDescription());
        product.setPriceBgn(addProductDTO.getPrice());
        BigDecimal priceEuro = addProductDTO.getPrice()
                .divide(BigDecimal.valueOf(1.95583), 2, RoundingMode.CEILING);
        product.setPriceEuro(priceEuro);
        product.setEnglishName(englishName);
        productRepository.save(product);
        System.out.println("Added product with name" + addProductDTO.getName());
        for (MultipartFile imageFile : imageFiles) {
            if (!imageFile.isEmpty()) {
                MultipartFile image = imageFile;
                String pictureName = getPicturePath(image, "ADMIN");
                String imagePath = IMAGE_PATH.substring(1);
                String urlName = addProductDTO.getName().toLowerCase().trim();

                try {
                    File file = new File(IMAGE_PATH + pictureName);
                    boolean mkdirs = file.getParentFile().mkdirs();
                    boolean newFile = file.createNewFile();

                    OutputStream outputStream = new FileOutputStream(file);
                    if (image.isEmpty()) {
                        System.out.println("EMPTY FILE");
                    }
                    outputStream.write(image.getBytes());
                    //logic
                    Picture picture = new Picture();
                    picture.setImagePath(imagePath + pictureName);
                    picture.setProduct(product);
                    pictureRepository.save(picture);
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private String getPicturePath(MultipartFile pictureFile, String username) {
        String[] pictureName = pictureFile.getOriginalFilename().split("\\.");
        String ext = pictureName[pictureName.length - 1];
        String pathPattern = "%s/%s." + ext;

        return String.format(pathPattern,
                username,
                UUID.randomUUID());
    }
}
