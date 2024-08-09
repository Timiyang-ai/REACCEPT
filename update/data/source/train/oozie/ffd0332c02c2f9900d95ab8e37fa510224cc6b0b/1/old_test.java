@Test
    public void testAddMissingPartition() throws MetadataServiceException, URISyntaxException {
        Services services = Services.get();
        PartitionDependencyManagerService pdms = services.get(PartitionDependencyManagerService.class);
        String newHCatDependency = "hcat://hcat.yahoo.com:5080/mydb/clicks/?datastamp=12&region=us";
        String actionId = "myAction";
        pdms.addMissingPartition(newHCatDependency, actionId);

        HCatURI hcatUri = new HCatURI(newHCatDependency);
        Map<String, PartitionsGroup> tablePartitionsMap = pdms.getHCatMap().get(hcatUri.getServer() + "#" +
                                                                            hcatUri.getDb()); // clicks
        assertNotNull(tablePartitionsMap);
        assertTrue(tablePartitionsMap.containsKey("clicks"));
        PartitionsGroup missingPartitions = tablePartitionsMap.get(hcatUri.getTable());
        assertNotNull(missingPartitions);

        assertEquals(missingPartitions.getPartitionsMap().keySet().iterator().next(),
                new PartitionWrapper(hcatUri)); // datastamp=12;region=us
        WaitingActions actions = missingPartitions.getPartitionsMap().get(new PartitionWrapper(hcatUri));
        assertNotNull(actions);
        assertTrue(actions.getActions().contains(actionId));
    }