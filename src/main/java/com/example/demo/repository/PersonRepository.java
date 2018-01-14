package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("PersonRepository")
public class PersonRepository implements DataRepository<Person>{

    @Autowired
    protected JdbcOperations operations;

    @Override
    public void put(Person entity) {
        Object[] params = new Object[] {entity.getName()};
        int[] types = new int[]{ Types.VARCHAR};
        operations.update("insert into person (name) values (?)", params, types);
    }

    @Override
    public void delete(Person entity) {
        operations.update("delete from person where id = " + entity.getId());

    }

    @Override
    public List<Person> getAll() {
        SqlRowSet sqlRowSet = operations.queryForRowSet("select * from person");
        List<Person> people = new ArrayList<>();
        while (sqlRowSet.next()){
            people.add(new Person(sqlRowSet.getInt("id"), sqlRowSet.getString("name")));
        }
        return people;
    }
}
