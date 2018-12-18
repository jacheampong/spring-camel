package com.learncamel.file;

import org.apache.camel.builder.RouteBuilder;


public class CopyFilesRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("file:data/input?noop=true")
                // use showHeaders  to log more info on data transfer
                .to("log:?level=INFO&showBody=true&showHeaders=true")
                .to("file:data/output");
    }

}
