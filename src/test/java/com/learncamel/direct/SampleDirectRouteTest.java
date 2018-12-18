package com.learncamel.direct;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.File;

public class SampleDirectRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() {
        return new SampleDirectRoute();
    }

    @Test
    public void sampleRouteTest() throws InterruptedException {

        // template: ProducerTemplate template in CamelTestSupport class
        template.sendBody("direct:sampleInput", "1234, Jonny Dev, Udemy Student");

        // sleep so files can be copied into directory
        Thread.sleep(5000);

        File file = new File("sampleOutput");
        assertTrue(file.isDirectory());

        Exchange exchange = consumer.receive("file:sampleOutput");
        assertEquals("output.txt", exchange.getIn().getHeader("CamelFileName"));
    }
}
