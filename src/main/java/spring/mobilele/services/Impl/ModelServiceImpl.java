package spring.mobilele.services.Impl;

import org.springframework.stereotype.Service;
import spring.mobilele.models.entities.Model;
import spring.mobilele.repositories.ModelRepository;
import spring.mobilele.services.ModelService;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }
}
