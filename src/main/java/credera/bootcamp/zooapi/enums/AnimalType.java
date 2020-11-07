package credera.bootcamp.zooapi.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Enumeration which controls the type of the animal:<br>" +
        "REPTILE - Animals in the class Reptilia<br>" +
        "INSECT - Animals in the class Arthropoda<br>" +
        "MAMMAL - Animals in the class Mammalia")
public enum AnimalType {
    REPTILE,
    INSECT,
    MAMMAL
}
