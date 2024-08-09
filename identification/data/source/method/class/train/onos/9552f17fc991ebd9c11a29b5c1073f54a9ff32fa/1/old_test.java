@Test
    public void getMappingEntriesByAddId() {
        addMapping(MAP_DATABASE, 1);
        addMapping(MAP_DATABASE, 2);

        assertTrue("should have two mappings",
                Lists.newLinkedList(
                        service.getMappingEntriesByAddId(MAP_DATABASE, appId)).size() == 2);
    }