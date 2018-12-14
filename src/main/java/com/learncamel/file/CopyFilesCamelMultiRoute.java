package com.learncamel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamelMultiRoute {

    public static void main(String[] args) {

        CamelContext camelContext = new DefaultCamelContext();

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    // copy files from 1 source dir to 2 destination dirs
                    from("file:data/input?noop=true")
                            // use showHeaders to log more info on data transfer
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            .to("file:data/output")
                    .to("file:data/output3");

                    from("file:data/input2?noop=true")
                            .to("file:data/output2");
                }
            });

            camelContext.start();

            // without the sleep, copying will not happen
            Thread.sleep(5000);

            camelContext.stop();

        } catch (Exception e) {
            System.out.println("Exception is : " + e);
            e.printStackTrace();
        }
    }
}
