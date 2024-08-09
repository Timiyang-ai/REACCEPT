@Test
    public void testGetCollections() throws Exception
    {
        new NonStrictExpectations(AuthorizeManager.class)
        {{
            // Allow current Community ADD perms
            AuthorizeManager.authorizeAction((Context) any, (Community) any,
                    Constants.ADD); result = null;
            // Allow current Community WRITE perms
            AuthorizeManager.authorizeAction((Context) any, (Community) any,
                    Constants.WRITE); result = null;
        }};

        //empty by default
        assertThat("testGetCollections 0",c.getCollections(), notNullValue());
        assertTrue("testGetCollections 1", c.getCollections().length == 0);

        Collection result = c.createCollection();
        assertThat("testGetCollections 2",c.getCollections(), notNullValue());
        assertTrue("testGetCollections 3", c.getCollections().length == 1);
        assertThat("testGetCollections 4",c.getCollections()[0], equalTo(result));
    }