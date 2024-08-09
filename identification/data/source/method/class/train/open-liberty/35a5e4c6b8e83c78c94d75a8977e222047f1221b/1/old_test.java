@Test
    public void testGetRequestStringForTrace2() {
        final String secret = "client_secret";
        final String pass = "password";
        final String url = "https://localhost:9080/target";
        final StringBuffer urlValue = new StringBuffer(url+"?"+secret+"="+pass);

        mock.checking(new Expectations() {
                {
                    allowing(request).getRequestURL();
                    will(returnValue(urlValue));
                    allowing(request).getQueryString
                        ();
                    will(returnValue(null));
                }
            });
        String output = WebUtils.getRequestStringForTrace(request,secret);
        assertNotNull(output);
        assertFalse(output.contains("password"));
        assertTrue(output.contains(url));
    }