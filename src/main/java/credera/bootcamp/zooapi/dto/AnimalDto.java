package credera.bootcamp.zooapi.dto;

import credera.bootcamp.zooapi.enums.AnimalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Record of an Animal")
public class AnimalDto {
    @Schema(description = "Identifier for Animal Record.")
    Long key;

    @Schema(description = "Name of the Animal.")
    String name;

    @Schema(description = "Age of the Animal.")
    Long age;

    @Schema(description = "Breed of the Animal.")
    String breed;

    @Schema(description = "Type of the Animal.")
    AnimalType animalType;
}
