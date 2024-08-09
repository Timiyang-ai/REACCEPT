@Test
    public void testGetSubcommunities() throws Exception
    {
        new NonStrictExpectations(authorizeService.getClass())
        {{
            // Allow current Community ADD perms
            authorizeService.authorizeAction((Context) any, (Community) any,
                    Constants.ADD); result = null;
            // Allow *parent* Community ADD perms
            authorizeService.authorizeActionBoolean((Context) any, (Community) any,
                    Constants.ADD); result = true;
        }};

        //empty by default
        assertThat("testGetSubcommunities 0",c.getSubcommunities(), notNullValue());
        assertTrue("testGetSubcommunities 1", c.getSubcommunities().size() == 0);

        //community with parent
        Community son = communityService.create(c, context);
        assertThat("testGetSubcommunities 2",c.getSubcommunities(), notNullValue());
        assertTrue("testGetSubcommunities 3", c.getSubcommunities().size() == 1);
        assertThat("testGetSubcommunities 4", c.getSubcommunities().get(0), equalTo(son));
    }