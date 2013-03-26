package com.mycompany.cxfTest;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import org.junit.Ignore;
import org.junit.Test;

import camelinaction.order.OrderEndpoint;


public class RouteTest extends CamelBlueprintTestSupport {
	
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }
    
    // should be the same address as we have in our route
    private static final String URL = "http://localhost:8678/CxfMtomRouterPayloadModeTest/jaxws-mtom/hello";
   
    protected static OrderEndpoint createCXFClient() {
        // we use CXF to create a client for us as its easier than JAXWS and works
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(OrderEndpoint.class);
        factory.setAddress(URL);
        return (OrderEndpoint) factory.create();
    }

    @Test
    public void testRoute() throws Exception {

        // create the webservice client and send the request
        OrderEndpoint client = createCXFClient();
        String out = client.order("car", 1, "milan");

        // assert we got a OK back
        assertEquals("OK", out);
        
    	assertEquals(1, 1);
    }

}
