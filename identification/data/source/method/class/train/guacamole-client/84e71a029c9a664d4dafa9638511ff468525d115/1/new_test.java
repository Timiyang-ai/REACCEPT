@Test
    public void testParseUserInfo() throws UnsupportedEncodingException {

        Map<String, String> userInfoMap;

        GuacamoleConfiguration config1 = new GuacamoleConfiguration();
        QCParser.parseUserInfo("guacuser:secretpw", config1);
        assertEquals("guacuser", config1.getParameter("username"));
        assertEquals("secretpw", config1.getParameter("password"));

        GuacamoleConfiguration config2 = new GuacamoleConfiguration();
        QCParser.parseUserInfo("guacuser", config2);
        assertEquals("guacuser", config2.getParameter("username"));
        assertNull(config2.getParameter("password"));

        GuacamoleConfiguration config3 = new GuacamoleConfiguration();
        QCParser.parseUserInfo("guacuser:P%40ssw0rd%21", config3);
        assertEquals("guacuser", config3.getParameter("username"));
        assertEquals("P@ssw0rd!", config3.getParameter("password"));

        GuacamoleConfiguration config4 = new GuacamoleConfiguration();
        QCParser.parseUserInfo("domain%5cguacuser:domain%2fpassword", config4);
        assertEquals("domain\\guacuser", config4.getParameter("username"));
        assertEquals("domain/password", config4.getParameter("password"));

    }