package team15.homelessapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team15.homelessapp.model.City;
import team15.homelessapp.repos.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(int id) {
        return cityRepository.findById(id);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> updateCity(int id, City updatedCity) {
        return cityRepository.findById(id).map(city -> {
            city.setCity_name(updatedCity.getCity_name());
            return cityRepository.save(city);
        });
    }

    public boolean deleteCity(int id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
