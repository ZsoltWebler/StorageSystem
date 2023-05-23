package org.webler.zsolt.storagesystem.json_example;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TestClassB {

    public int id;
    public String name;
    public List<TestClassA> as;
}
