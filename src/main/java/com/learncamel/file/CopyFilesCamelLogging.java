package com.learncamel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamelLogging {

    public static void main(String[] args) {

        CamelContext camelContext = new DefaultCamelContext();

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:data/input?noop=true")
                            // use showHeaders  to log more info on data transfer
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            .to("file:data/output");
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
