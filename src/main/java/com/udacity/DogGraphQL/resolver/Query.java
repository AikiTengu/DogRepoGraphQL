package com.udacity.DogGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.DogGraphQL.entity.Dog;
import com.udacity.DogGraphQL.exception.DogNotFoundException;
import com.udacity.DogGraphQL.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    //example query: "{ query: findAllDogs { id breed } }"

    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    //example query: "{ query: findDobById(id:5) { id breed } }"
    
    public Dog findDogById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
