@Test
    public void testGetUntaggedName() throws VersioningSyntaxException {

        // test an application name that contains a version expression
        // application name : foo:RC-*
        String expression = APPLICATION_NAME
                + VersioningService.EXPRESSION_SEPARATOR + "RC-"
                + VersioningService.EXPRESSION_WILDCARD;

        String result = VersioningService.getUntaggedName(expression);
        assertEquals(APPLICATION_NAME, result);

        // test an application name that contains a version identifier
        // application name : foo:RC-1.0.0
        expression = APPLICATION_NAME
                + VersioningService.EXPRESSION_SEPARATOR + "RC-1.0.0";

        result = VersioningService.getUntaggedName(expression);
        assertEquals(APPLICATION_NAME, result);

        // test an application name that is an untagged version name
        // application name : foo
        expression = APPLICATION_NAME;

        result = VersioningService.getUntaggedName(expression);
        assertEquals(APPLICATION_NAME, result);

        // test an application name containing a critical pattern
        // application name : foo:
        expression = APPLICATION_NAME + VersioningService.EXPRESSION_SEPARATOR;

        try {
            result = VersioningService.getUntaggedName(expression);
            fail("the getUntagged method did not throw a VersioningSyntaxException");
        }
        catch(VersioningSyntaxException e){}
    }