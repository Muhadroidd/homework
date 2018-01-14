package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.exception.RestException;
import com.example.demo.service.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DataController extends ExceptionHandlerController{
    private static final Logger LOG = Logger.getLogger(DataController.class);

    @Autowired
    @Qualifier("dataService")
    private DataService dataService;

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> put (@RequestBody Person person) throws RestException{
        try {
            if (person == null || person.getName().equals("")){
                return Ajax.emptyResponse();
            }
            dataService.put(person);
            return Ajax.emptyResponse();
        }catch (Exception e){
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllPeople () throws RestException{
        try {
            List<String> result = dataService.getNames();
            return Ajax.successResponse(result);
        }catch (Exception e){
            throw new RestException(e);
        }
    }

}
