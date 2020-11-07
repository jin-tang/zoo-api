package credera.bootcamp.zooapi.domain;

import credera.bootcamp.zooapi.enums.AnimalType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(
        name = Animal.TABLE_NAME,
        indexes = { }
)
public class Animal {
    public static final String TABLE_NAME = "ANIMAL";

    @Id
    @GeneratedValue(generator = TABLE_NAME + "_GENERATOR")
    @SequenceGenerator(
            name = TABLE_NAME + "_GENERATOR",
            sequenceName = TABLE_NAME + "_SEQUENCE"
    )
    @Column(name = TABLE_NAME + "_KEY", nullable = false)
    Long key;

    @Column(name = TABLE_NAME + "_NAME", nullable = false, length = 80)
    String name;

    @Column(name = TABLE_NAME + "_AGE", nullable = false)
    Long age;

    @Column(name = TABLE_NAME + "_BREED", nullable = false, length = 80)
    String breed;

    @Column(name = TABLE_NAME + "_TYPE", nullable = false, length = 40)
    @Enumerated(value = EnumType.STRING)
    AnimalType type;
}

