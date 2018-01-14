package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("dataService")
public class PersonService implements DataService{

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    @Qualifier("PersonRepository")
    private DataRepository dataRepository;


    @Override
    public boolean put(Person person) {
        try {
            dataRepository.put(person);
            return true;
        }catch (Exception e){
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<String> getNames() {
        List<Person> people = dataRepository.getAll();
        List<String> names = new ArrayList<>();
        for (Person p : people){
            names.add(p.getName());
        }
        return names;
    }
}