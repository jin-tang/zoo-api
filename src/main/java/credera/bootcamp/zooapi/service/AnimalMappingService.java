package credera.bootcamp.zooapi.service;

import credera.bootcamp.zooapi.domain.Animal;
import credera.bootcamp.zooapi.dto.AnimalDto;
import org.springframework.stereotype.Service;

@Service
public class AnimalMappingService {

    public AnimalDto buildAnimalDto(Animal animal) {
        return AnimalDto.builder()
                .key(animal.getKey())
                .name(animal.getName())
                .age(animal.getAge())
                .breed(animal.getBreed())
                .animalType(animal.getType())
                .build();
    }

    public Animal buildAnimal(AnimalDto animalDto) {
        var animal = new Animal();
        animal.setKey(animalDto.getKey());
        animal.setName(animalDto.getName());
        animal.setAge(animalDto.getAge());
        animal.setBreed(animalDto.getBreed());
        animal.setType(animalDto.getAnimalType());
        return animal;
    }
}
