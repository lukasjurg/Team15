package team15.homelessapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team15.homelessapp.model.ServiceCategory;
import team15.homelessapp.repos.ServiceCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCategoryService {

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public List<ServiceCategory> getAllServiceCategories() {
        return serviceCategoryRepository.findAll();
    }

    public Optional<ServiceCategory> getServiceCategoryById(int id) {
        return serviceCategoryRepository.findById(id);
    }

    public ServiceCategory createServiceCategory(ServiceCategory category) {
        return serviceCategoryRepository.save(category);
    }

    public Optional<ServiceCategory> updateServiceCategory(int id, ServiceCategory updatedCategory) {
        return serviceCategoryRepository.findById(id).map(category -> {
            category.setCategory_name(updatedCategory.getCategory_name());
            category.setCategory_description(updatedCategory.getCategory_description());
            return serviceCategoryRepository.save(category);
        });
    }

    public boolean deleteServiceCategory(int id) {
        if (serviceCategoryRepository.existsById(id)) {
            serviceCategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
