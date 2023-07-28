package com.mrmk.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public SomeBean filtering() {
        return new SomeBean("value1", "values2", "value3");
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue filteringDynamic() {
        SomeBean someBean = new SomeBean("value1", "values2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept("feild2");
        FilterProvider filer = new SimpleFilterProvider().addFilter("SomeBeanFilter", filters);
        mappingJacksonValue.setFilters(filer);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBean = Arrays.asList(new SomeBean("value1", "values2", "value3"), new SomeBean("value4", "values2", "value6"), new SomeBean("value7", "values2", "value9"), new SomeBean("value1", "values2", "value3"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept("feild1", "feild2");
        FilterProvider filer = new SimpleFilterProvider().addFilter("SomeBeanFilter", filters);
        mappingJacksonValue.setFilters(filer);

        return mappingJacksonValue;
    }

}
