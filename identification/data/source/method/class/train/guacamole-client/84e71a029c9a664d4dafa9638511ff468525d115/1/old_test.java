@Test
    public void testParseUserInfo() throws UnsupportedEncodingException {

        Map<String, String> userInfoMap;

        userInfoMap = QCParser.parseUserInfo("guacuser:secretpw");
        assertEquals("guacuser", userInfoMap.get("username"));
        assertEquals("secretpw", userInfoMap.get("password"));


        userInfoMap = QCParser.parseUserInfo("guacuser");
        assertEquals("guacuser", userInfoMap.get("username"));
        assertFalse(userInfoMap.containsKey("password"));

        userInfoMap = QCParser.parseUserInfo("guacuser:P%40ssw0rd%21");
        assertEquals("guacuser", userInfoMap.get("username"));
        assertEquals("P@ssw0rd!", userInfoMap.get("password"));

        userInfoMap = QCParser.parseUserInfo("domain%5cguacuser:domain%2fpassword");
        assertEquals("domain\\guacuser", userInfoMap.get("username"));
        assertEquals("domain/password", userInfoMap.get("password"));

    }