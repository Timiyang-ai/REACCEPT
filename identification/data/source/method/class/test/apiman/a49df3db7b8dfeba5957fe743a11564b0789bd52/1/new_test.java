@Test
    public void testParseServiceRequestPath() {
        ServiceRequestPathInfo info = parseServiceRequestPath(null);

        info = parseServiceRequestPath("/invalidpath");
        Assert.assertNull(info.orgId);
        Assert.assertNull(info.serviceId);
        Assert.assertNull(info.serviceVersion);
        Assert.assertNull(info.resource);

        info = parseServiceRequestPath("/invalid/path");
        Assert.assertNull(info.orgId);
        Assert.assertNull(info.serviceId);
        Assert.assertNull(info.serviceVersion);
        Assert.assertNull(info.resource);

        info = parseServiceRequestPath("/Org1/Service1/1.0");
        Assert.assertEquals("Org1", info.orgId);
        Assert.assertEquals("Service1", info.serviceId);
        Assert.assertEquals("1.0", info.serviceVersion);
        Assert.assertNull(info.resource);

        info = parseServiceRequestPath("/MyOrg/Service-99/2.7");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertNull(info.resource);

        info = parseServiceRequestPath("/MyOrg/Service-99/2.7/resource");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/resource", info.resource);

        info = parseServiceRequestPath("/MyOrg/Service-99/2.7/path/to/resource");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/path/to/resource", info.resource);

        info = parseServiceRequestPath("/MyOrg/Service-99/2.7/path/to/resource?query=1234");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/path/to/resource?query=1234", info.resource);

        info = parseServiceRequestPath("/MyOrg/Service-99/path/to/resource?query=1234", null, "2.7");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/path/to/resource?query=1234", info.resource);

        info = parseServiceRequestPath("/MyOrg/Service-99/path/to/resource?query=1234", "application/apiman.2.7+json", null);
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Service-99", info.serviceId);
        Assert.assertEquals("2.7", info.serviceVersion);
        Assert.assertEquals("/path/to/resource?query=1234", info.resource);

    }