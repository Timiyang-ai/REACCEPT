@Test
    public void testGenerate() throws Exception {
        // Just make sure that something got picked for every table+procedure
        PartitionPlan pplan = this.partitioner.generate(this.hints);
        assertNotNull(pplan);
        assertEquals(catalog_db.getTables().size(), pplan.getTableEntries().size());
        Collection<PartitionMethodType> allowed_types = null;
        
        // TABLES
        allowed_types = CollectionUtil.addAll(new HashSet<PartitionMethodType>(), PartitionMethodType.HASH,
                                                                                  PartitionMethodType.REPLICATION,
                                                                                  PartitionMethodType.MAP);
        for (Entry<Table,PartitionEntry> e : pplan.getTableEntries().entrySet()) {
            assertNotNull(e.getKey());
            assertNotNull("Null PartitionEntry for " + e.getKey(), e.getValue());
            assert(allowed_types.contains(e.getValue().getMethod())) : "Unexpected: " + e.getValue().getMethod();
            if (e.getValue().getMethod() != PartitionMethodType.REPLICATION) assertNotNull("Null attribute for " + e.getValue(), e.getValue().getAttribute());
        } // FOR
        
        // PROCEDURES
        allowed_types = CollectionUtil.addAll(new HashSet<PartitionMethodType>(), PartitionMethodType.HASH,
                                                                                  PartitionMethodType.NONE);
        for (Entry<Procedure, PartitionEntry> e : pplan.getProcedureEntries().entrySet()) {
            assertNotNull(e.getKey());
            assertNotNull("Null PartitionEntry for " + e.getKey(), e.getValue());
            assert(allowed_types.contains(e.getValue().getMethod())) : "Unexpected: " + e.getValue().getMethod();
            if (e.getValue().getMethod() != PartitionMethodType.NONE) assertNotNull(e.getValue().getAttribute());
        } // FOR
        
        // System.err.println(pplan);
    }