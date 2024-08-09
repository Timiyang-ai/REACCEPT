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

        context.turnOffAuthorisationSystem();
        Collection collection = collectionService.create(context, c);
        collectionService.setMetadataSingleValue(context, collection, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "collection B");
        collection = collectionService.create(context, c);
        collectionService.setMetadataSingleValue(context, collection, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "collection C");
        collection = collectionService.create(context, c);
        collectionService.setMetadataSingleValue(context, collection, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY, "collection A");
        //we need to commit the changes so we don't block the table for testing
        context.restoreAuthSystemState();

        //sorted
        assertTrue("testGetCollections 2",c.getCollections().get(0).getName().equals("collection A"));
        assertTrue("testGetCollections 3",c.getCollections().get(1).getName().equals("collection B"));
        assertTrue("testGetCollections 4",c.getCollections().get(2).getName().equals("collection C"));
    }