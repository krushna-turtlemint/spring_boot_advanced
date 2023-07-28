package com.mrmk.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
    @GetMapping("/v1/person")
    public Person1 getinfo() {
        return new Person1("Krushna");
    }

    @GetMapping("/v2/person")
    public Person2 getinfov2() {
        return new Person2(new Name("Krushna", "mali"));
    }
    @GetMapping(path = "/person", params = "version=1")
    public Person1 getinfoRequestParam() {
        return new Person1("Krushna");
    }

    @GetMapping(path = "/person", params = "version=2")
    public Person2 getinfoRequestParam2() {
        return new Person2(new Name("Krushna", "mali"));
    }
}
