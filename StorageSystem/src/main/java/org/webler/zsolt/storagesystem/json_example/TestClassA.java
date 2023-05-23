package org.webler.zsolt.storagesystem.json_example;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TestClassA {

    public int id;
    public String name;
    public TestClassB b;
}
