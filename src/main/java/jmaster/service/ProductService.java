package jmaster.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import jmaster.dao.ProductDao;
import jmaster.entity.Product;
import jmaster.model.ProductDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface ProductService {
    void insert(ProductDTO productDTO);

    ProductDTO get(Long id);

    List<ProductDTO> getAll();

}

@Transactional
@Service
class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public void insert(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productDao.insert(product);
    }

    @Override
    public ProductDTO get(Long id) {
        Product product = productDao.get(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productDao.getAll();
        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for (Product product : products) {
            productDTOS.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOS;
    }
}
