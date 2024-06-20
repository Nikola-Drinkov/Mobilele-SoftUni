package spring.mobilele.services.Impl;

import org.springframework.stereotype.Service;
import spring.mobilele.models.entities.Brand;
import spring.mobilele.repositories.BrandRepository;
import spring.mobilele.services.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
