@Test
    public void testGetCollections() throws Exception
    {
        new NonStrictExpectations(authorizeService.getClass())
        {{
            // Allow current Community ADD perms
            authorizeService.authorizeAction((Context) any, (Community) any,
                    Constants.ADD); result = null;
            // Allow current Community WRITE perms
            authorizeService.authorizeAction((Context) any, (Community) any,
                    Constants.WRITE); result = null;
        }};

        //empty by default
        assertThat("testGetCollections 0",c.getCollections(), notNullValue());
        assertTrue("testGetCollections 1", c.getCollections().size() == 0);

        Collection result = collectionService.create(context, c);
        assertThat("testGetCollections 2",c.getCollections(), notNullValue());
        assertTrue("testGetCollections 3", c.getCollections().size() == 1);
        assertThat("testGetCollections 4",c.getCollections().get(0), equalTo(result));
    }