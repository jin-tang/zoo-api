package credera.bootcamp.zooapi.resource;

import credera.bootcamp.zooapi.domain.Animal;
import credera.bootcamp.zooapi.dto.AnimalDto;
import credera.bootcamp.zooapi.enums.AnimalType;
import credera.bootcamp.zooapi.service.AnimalMappingService;
import credera.bootcamp.zooapi.service.AnimalService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static credera.bootcamp.zooapi.service.AnimalMappingService.buildAnimalDto;

@RestController
@RequestMapping("/animals")
public class AnimalResource {
    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalMappingService animalMappingService;

    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }

    @GetMapping
    public Page<AnimalDto> getAllAnimals(Pageable page) {
        Page<Animal> allAnimals = animalService.getAllAnimals(page);
        return allAnimals.map(AnimalMappingService::buildAnimalDto);
    }

    @GetMapping("/filter")
    public List<AnimalDto> filterAnimals(
            @RequestParam(value = "breed", required = false) String breed,
            @RequestParam(value = "type", required = false) AnimalType type) {
        List<Animal> animals = animalService.getFilteredAnimals(breed, type);
        return animals.stream().map(AnimalMappingService::buildAnimalDto).collect(Collectors.toList());
    }

    @GetMapping("/{animalKey}")
    public AnimalDto getAnimal(@PathVariable("animalKey") Long animalKey) {
        Animal animal = animalService
                .getAnimal(animalKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Did not find animal with key: " + animalKey
                ));

        return buildAnimalDto(animal);
    }

    @PostMapping
    public AnimalDto createAnimal(@RequestBody AnimalDto animalDto) {
        if (!EnumUtils.isValidEnum(AnimalType.class, animalDto.getAnimalType().name())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot save animal of type: " + animalDto.getAnimalType());
        }
        Animal animal = animalService.createAnimal(animalDto);
        return buildAnimalDto(animal);
    }

    @PutMapping("/{animalKey}")
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable("animalKey") Long animalKey, @RequestBody AnimalDto animalDto) {
        animalService
                .getAnimal(animalKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Did not find animal with key: " + animalKey
                ));
        try {
            Animal animal = animalService.updateAnimal(animalDto);
            AnimalDto dto = buildAnimalDto(animal);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot contain special characters or numbers: " + animalDto.getBreed());
        }
    }

    @DeleteMapping("/{animalKey}")
    public ResponseEntity deleteAnimal(@PathVariable("animalKey") Long animalKey) {
        Animal animal = animalService
                .getAnimal(animalKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Did not find animal with key: " + animalKey
                ));
        animalService.deleteAnimal(animal);
        return ResponseEntity.noContent().build();
    }
}
