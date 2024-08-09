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

        context.turnOffAuthorisationSystem();
        Community community = communityService.create(c, context);
        communityService.setMetadataSingleValue(context, community, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "subcommunity B");
        community = communityService.create(c, context);
        communityService.setMetadataSingleValue(context, community, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "subcommunity A");
        community = communityService.create(c, context);
        communityService.setMetadataSingleValue(context, community, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "subcommunity C");
        //we need to commit the changes so we don't block the table for testing
        context.restoreAuthSystemState();

        //get Subcommunities sorted
        assertTrue("testGetCollections 2",c.getSubcommunities().get(0).getName().equals("subcommunity A"));
        assertTrue("testGetCollections 3",c.getSubcommunities().get(1).getName().equals("subcommunity B"));
        assertTrue("testGetCollections 4",c.getSubcommunities().get(2).getName().equals("subcommunity C"));
    }