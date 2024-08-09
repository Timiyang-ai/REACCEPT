@Test
    public void testGetCommunities() throws Exception
    {
        context.turnOffAuthorisationSystem();
        Community community = communityService.create(null, context);
        communityService.setMetadataSingleValue(context, community, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "community 3");
        this.collection.addCommunity(community);
        community = communityService.create(null, context);
        communityService.setMetadataSingleValue(context, community, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "community 1");
        this.collection.addCommunity(community);
        community = communityService.create(null, context);
        communityService.setMetadataSingleValue(context, community, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "community 2");
        this.collection.addCommunity(community);
        context.restoreAuthSystemState();
        assertTrue("testGetCommunities 0",collection.getCommunities().size() == 4);
        //Communities should be sorted by name
        assertTrue("testGetCommunities 1",collection.getCommunities().get(1).getName().equals("community 1"));
        assertTrue("testGetCommunities 1",collection.getCommunities().get(2).getName().equals("community 2"));
        assertTrue("testGetCommunities 1",collection.getCommunities().get(3).getName().equals("community 3"));
    }