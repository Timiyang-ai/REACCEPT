@Test
    public void testParse1() throws Exception {
        final String headerValue = "custno = 12345; comment=test; version=1," +
            " name=John; version=1; max-age=600; secure; domain=.apache.org";

        final Header header = new BasicHeader("set-cookie", headerValue);

        final CookieSpec cookiespec = new BrowserCompatSpec();
        final CookieOrigin origin = new CookieOrigin("www.apache.org", 80, "/", false);
        final List<Cookie> cookies = cookiespec.parse(header, origin);
        for (int i = 0; i < cookies.size(); i++) {
            cookiespec.validate(cookies.get(i), origin);
        }
        Assert.assertEquals(2, cookies.size());

        Assert.assertEquals("custno", cookies.get(0).getName());
        Assert.assertEquals("12345", cookies.get(0).getValue());
        Assert.assertEquals("test", cookies.get(0).getComment());
        Assert.assertEquals(1, cookies.get(0).getVersion());
        Assert.assertEquals("www.apache.org", cookies.get(0).getDomain());
        Assert.assertEquals("/", cookies.get(0).getPath());
        Assert.assertFalse(cookies.get(0).isSecure());

        Assert.assertEquals("name", cookies.get(1).getName());
        Assert.assertEquals("John", cookies.get(1).getValue());
        Assert.assertEquals(null, cookies.get(1).getComment());
        Assert.assertEquals(1, cookies.get(1).getVersion());
        Assert.assertEquals(".apache.org", cookies.get(1).getDomain());
        Assert.assertEquals("/", cookies.get(1).getPath());
        Assert.assertTrue(cookies.get(1).isSecure());
    }