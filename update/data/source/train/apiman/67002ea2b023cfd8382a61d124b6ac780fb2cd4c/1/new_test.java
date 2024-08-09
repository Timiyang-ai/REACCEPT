@Test
    public void testParseApiRequestPath() {
        ApiRequestPathInfo info = parseApiRequestPath(null);

        info = parseApiRequestPath("/invalidpath");
        Assert.assertNull(info.orgId);
        Assert.assertNull(info.apiId);
        Assert.assertNull(info.apiVersion);
        Assert.assertNull(info.resource);

        info = parseApiRequestPath("/invalid/path");
        Assert.assertNull(info.orgId);
        Assert.assertNull(info.apiId);
        Assert.assertNull(info.apiVersion);
        Assert.assertNull(info.resource);

        info = parseApiRequestPath("/Org1/Api1/1.0");
        Assert.assertEquals("Org1", info.orgId);
        Assert.assertEquals("Api1", info.apiId);
        Assert.assertEquals("1.0", info.apiVersion);
        Assert.assertNull(info.resource);

        info = parseApiRequestPath("/MyOrg/Api-99/2.7");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Api-99", info.apiId);
        Assert.assertEquals("2.7", info.apiVersion);
        Assert.assertNull(info.resource);

        info = parseApiRequestPath("/MyOrg/Api-99/2.7/resource");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Api-99", info.apiId);
        Assert.assertEquals("2.7", info.apiVersion);
        Assert.assertEquals("/resource", info.resource);

        info = parseApiRequestPath("/MyOrg/Api-99/2.7/path/to/resource");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Api-99", info.apiId);
        Assert.assertEquals("2.7", info.apiVersion);
        Assert.assertEquals("/path/to/resource", info.resource);

        info = parseApiRequestPath("/MyOrg/Api-99/2.7/path/to/resource?query=1234");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Api-99", info.apiId);
        Assert.assertEquals("2.7", info.apiVersion);
        Assert.assertEquals("/path/to/resource?query=1234", info.resource);

        info = parseApiRequestPath("/MyOrg/Api-99/path/to/resource?query=1234", null, "2.7");
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Api-99", info.apiId);
        Assert.assertEquals("2.7", info.apiVersion);
        Assert.assertEquals("/path/to/resource?query=1234", info.resource);

        info = parseApiRequestPath("/MyOrg/Api-99/path/to/resource?query=1234", "application/apiman.2.7+json", null);
        Assert.assertEquals("MyOrg", info.orgId);
        Assert.assertEquals("Api-99", info.apiId);
        Assert.assertEquals("2.7", info.apiVersion);
        Assert.assertEquals("/path/to/resource?query=1234", info.resource);

    }