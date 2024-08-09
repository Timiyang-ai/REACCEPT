@Test
    public void testRemovePartition() throws MetadataServiceException, URISyntaxException {
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

        // remove with cascading - OFF
        pdms.removePartition(newHCatDependency, false);
        assertFalse(missingPartitions.getPartitionsMap().containsKey(hcatUri.getPartitionMap()));

        pdms.addMissingPartition(newHCatDependency, actionId);
        assertNotNull(missingPartitions);

        // remove with cascading - ON
        pdms.removePartition(newHCatDependency);
        assertFalse(pdms.getHCatMap().containsKey(hcatUri.getTable()));
    }