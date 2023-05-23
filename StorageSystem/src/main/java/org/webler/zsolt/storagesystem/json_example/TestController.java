package org.webler.zsolt.storagesystem.json_example;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {




    @GetMapping
    public List<TestClassB> getStorages() {

        ArrayList<TestClassB> bs = new ArrayList<>();
        TestClassB testClassB = new TestClassB();
        testClassB.id = 1;
        testClassB.name = "test_B";
        TestClassA testClassA = new TestClassA();
        testClassA.name = "test_a_1";
        testClassA.id = 1;
        testClassA.b = testClassB;
        TestClassA testClassA2 = new TestClassA();
        testClassA2.name = "test_a_1";
        testClassA2.id = 2;
        testClassA2.b = testClassB;
        testClassB.as = new ArrayList<>(Arrays.asList(testClassA,testClassA2));
        bs.add(testClassB);
        return bs;

    }
}
