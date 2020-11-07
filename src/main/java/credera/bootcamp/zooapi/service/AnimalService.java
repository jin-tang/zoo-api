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

    public AnimalDto createAnimal(AnimalDto animalDto) {
        var animal = animalMappingService.buildAnimal(animalDto);
        animalRepository.save(animal);
        return animalMappingService.buildAnimalDto(animal);
    }

    public AnimalDto updateAnimal(AnimalDto animalDto) {
        var animal = animalMappingService.buildAnimal(animalDto);
        animalRepository.save(animal);
        return animalMappingService.buildAnimalDto(animal);
    }

    public void deleteAnimal(AnimalDto animalDto) {
        var animal = animalMappingService.buildAnimal(animalDto);
        animalRepository.delete(animal);
    }
}
