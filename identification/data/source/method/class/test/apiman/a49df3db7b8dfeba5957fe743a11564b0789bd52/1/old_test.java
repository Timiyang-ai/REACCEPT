@Test
    public void testParseServiceRequestPath() {
        ServiceRequestPathInfo info = GatewayServlet.parseServiceRequestPath(null);

        info = GatewayServlet.parseServiceRequestPath("/invalidpath");
        Assert.assertNull(info.orgId);
        Assert.assertNull(info.serviceId);
        Assert.assertNull(info.serviceVersion);
        Assert.assertNull(info.resource);

        info = GatewayServlet.parseServiceRequestPath("/invalid/path");
        Assert.assertNull(info.orgId);
        Assert.assertNull(info.serviceId);
        Assert.assertNull(info.serviceVersion);
        Assert.assertNull(info.resource);

        info = GatewayServlet.parseServiceRequestPath("/Org1/Service1/1.0");
        Assert.assertEquals("Org1", info.orgId);
        Assert.assertEquals("Service1", info.serviceId);
        Assert.assertEquals("1.0", info.serviceVersion);
        Assert.assertNull(info.resource);

        info = GatewayServlet.parseServiceRequestPath("/MyOrg/Service-99/2.7");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertNull(info.resource);

        info = GatewayServlet.parseServiceRequestPath("/MyOrg/Service-99/2.7/resource");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/resource", info.resource);

        info = GatewayServlet.parseServiceRequestPath("/MyOrg/Service-99/2.7/path/to/resource");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/path/to/resource", info.resource);

        info = GatewayServlet.parseServiceRequestPath("/MyOrg/Service-99/2.7/path/to/resource?query=1234");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/path/to/resource?query=1234", info.resource);
    }