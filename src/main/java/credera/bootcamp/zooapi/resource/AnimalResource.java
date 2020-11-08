package credera.bootcamp.zooapi.resource;

import credera.bootcamp.zooapi.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalResource {
    @Autowired
    private AnimalService animalService;

    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }

    // TODO Implement me!
    public String getAnimal() {
        return "Get an animal!";
    }

    // TODO Implement me!
    public String createAnimal() {
        return "Create an animal!";
    }

    // TODO Implement me!
    public String updateAnimal() {
        return "Update an animal!";
    }

    // TODO Implement me!
    public String deleteAnimal() {
        return "Delete an animal!";
    }
}
