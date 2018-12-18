package com.learncamel.direct;

import org.apache.camel.builder.RouteBuilder;

public class SampleDirectRoute extends RouteBuilder {

    public void configure() {
        from("direct:sampleInput")
                .log("Received Message is ${body} and Headers are ${headers}")
                .to("file:sampleOutput?fileName=output.txt");
    }

}
