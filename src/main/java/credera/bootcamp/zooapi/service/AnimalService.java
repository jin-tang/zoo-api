package credera.bootcamp.zooapi.service;

import credera.bootcamp.zooapi.domain.Animal;
import credera.bootcamp.zooapi.dto.AnimalDto;
import credera.bootcamp.zooapi.enums.AnimalType;
import credera.bootcamp.zooapi.repository.AnimalRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static credera.bootcamp.zooapi.service.AnimalMappingService.buildAnimal;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalMappingService animalMappingService;

    @Transactional(readOnly = true)
    public Page<Animal> getAllAnimals(Pageable page) { return animalRepository.findAll(page); }

    @Transactional(readOnly = true)
    public List<Animal> getFilteredAnimals(String breed, AnimalType type) {
        List<Animal> animals = animalRepository.findAll();
        Stream<Animal> stream = animals.stream();
        if(StringUtils.isNotBlank(breed)) {
            stream = stream.filter(animal -> breed.equals(animal.getBreed()));
        }
        if(type != null) {
            stream = stream.filter(animal -> type.equals(animal.getType()));
        }
        return stream.collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Animal> getAnimal(Long animalKey) {
        return animalRepository.findById(animalKey);
    }

    @Transactional
    public Animal createAnimal(AnimalDto animalDto) {
        Animal animal = buildAnimal(animalDto);
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal updateAnimal(AnimalDto animalDto) throws Exception {
        Pattern regex = Pattern.compile("^[A-Za-z]+$");
        Matcher match = regex.matcher(animalDto.getBreed());
        if(match.matches()) {
            throw new Exception("No special characters or numbers allow");
        }
        Animal animal = buildAnimal(animalDto);
        return animalRepository.save(animal);
    }

    @Transactional
    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal);
    }
}
