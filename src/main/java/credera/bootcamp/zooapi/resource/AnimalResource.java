package credera.bootcamp.zooapi.resource;

import credera.bootcamp.zooapi.dto.AnimalDto;
import credera.bootcamp.zooapi.enums.AnimalType;
import credera.bootcamp.zooapi.service.AnimalMappingService;
import credera.bootcamp.zooapi.service.AnimalService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{animalKey}")
    public AnimalDto getAnimal(@PathVariable("animalKey") Long animalKey) {
        var animal = animalService
                .getAnimal(animalKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Did not find animal with key: " + animalKey
                ));

        return animalMappingService.buildAnimalDto(animal);
    }

    @PostMapping
    public AnimalDto createAnimal(@RequestBody AnimalDto animalDto) {
        if (!EnumUtils.isValidEnum(AnimalType.class, animalDto.getAnimalType().name())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot save animal of type: " + animalDto.getAnimalType());
        }
        var animal = animalService.createAnimal(animalDto);
        return animalMappingService.buildAnimalDto(animal);
    }

    @PutMapping("/{animalKey}")
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable("animalKey") Long animalKey, @RequestBody AnimalDto animalDto) {
        var animal = animalService
                .getAnimal(animalKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Did not find animal with key: " + animalKey
                ));
        animal = animalService.updateAnimal(animalDto);
        var dto = animalMappingService.buildAnimalDto(animal);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{animalKey}")
    public ResponseEntity deleteAnimal(@PathVariable("animalKey") Long animalKey) {
        var animal = animalService
                .getAnimal(animalKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Did not find animal with key: " + animalKey
                ));
        animalService.deleteAnimal(animal);
        return ResponseEntity.noContent().build();
    }
}
