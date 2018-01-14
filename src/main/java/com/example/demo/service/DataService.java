package com.example.demo.service;


import com.example.demo.entity.Person;

import java.util.List;

public interface DataService {

    boolean put(Person person);

    List<String> getNames ();
 }
