@Test
    public void testParseApiRequestQueryParams() {
        QueryMap paramMap = GatewayServlet.parseApiRequestQueryParams(null);
        Assert.assertNotNull(paramMap);

        paramMap = GatewayServlet.parseApiRequestQueryParams("param1");
        Assert.assertNull(paramMap.get("param1"));

        paramMap = GatewayServlet.parseApiRequestQueryParams("param1=value1");
        Assert.assertEquals("value1", paramMap.get("param1"));

        paramMap = GatewayServlet.parseApiRequestQueryParams("param1=value1&param2");
        Assert.assertEquals("value1", paramMap.get("param1"));
        Assert.assertNull(paramMap.get("param2"));

        paramMap = GatewayServlet.parseApiRequestQueryParams("param1=value1&param2=value2");
        Assert.assertEquals("value1", paramMap.get("param1"));
        Assert.assertEquals("value2", paramMap.get("param2"));

        paramMap = GatewayServlet.parseApiRequestQueryParams("param1=value1&param2=value2&param3=value3");
        Assert.assertEquals("value1", paramMap.get("param1"));
        Assert.assertEquals("value2", paramMap.get("param2"));
        Assert.assertEquals("value3", paramMap.get("param3"));

        paramMap = GatewayServlet.parseApiRequestQueryParams("param1=hello%20world&param2=hello+world&param3=hello world");
        Assert.assertEquals("hello world", paramMap.get("param1"));
        Assert.assertEquals("hello world", paramMap.get("param2"));
        Assert.assertEquals("hello world", paramMap.get("param3"));
    }