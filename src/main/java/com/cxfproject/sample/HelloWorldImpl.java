
package com.cxfproject.sample;

import javax.jws.WebService;

@WebService(endpointInterface = "com.cxfproject.sample.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

