package credera.bootcamp.zooapi.service;

import credera.bootcamp.zooapi.domain.Animal;
import credera.bootcamp.zooapi.dto.AnimalDto;
import credera.bootcamp.zooapi.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalMappingService animalMappingService;

    public Optional<Animal> getAnimal(Long animalKey) {
        return animalRepository.findById(animalKey);
    }

    public Animal createAnimal(AnimalDto animalDto) {
        var animal = animalMappingService.buildAnimal(animalDto);
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(AnimalDto animalDto) {
        var animal = animalMappingService.buildAnimal(animalDto);
        return animalRepository.save(animal);
    }

    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal);
    }
}
