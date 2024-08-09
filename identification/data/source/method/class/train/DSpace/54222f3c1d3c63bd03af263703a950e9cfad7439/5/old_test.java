@Test
    public void testGetSubcommunities() throws Exception
    {
        new NonStrictExpectations(AuthorizeManager.class)
        {{
            // Allow current Community ADD perms
            AuthorizeManager.authorizeAction((Context) any, (Community) any,
                    Constants.ADD); result = null;
            // Allow *parent* Community ADD perms
            AuthorizeManager.authorizeActionBoolean((Context) any, (Community) any,
                    Constants.ADD); result = true;
        }};

        //empty by default
        assertThat("testGetSubcommunities 0",c.getSubcommunities(), notNullValue());
        assertTrue("testGetSubcommunities 1", c.getSubcommunities().length == 0);

        //community with parent
        Community son = Community.create(c, context);
        assertThat("testGetSubcommunities 2",c.getSubcommunities(), notNullValue());
        assertTrue("testGetSubcommunities 3", c.getSubcommunities().length == 1);
        assertThat("testGetSubcommunities 4", c.getSubcommunities()[0], equalTo(son));
    }