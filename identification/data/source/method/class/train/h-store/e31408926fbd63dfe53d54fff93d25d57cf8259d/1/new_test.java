@Test
    public void testGenerate() throws Exception {
        PartitionPlan pplan = this.partitioner.generate(this.hints);
        assertNotNull(pplan);
        assertEquals(catalogContext.getDataTables().size(), pplan.getTableEntries().size());

        for (Table catalog_tbl : pplan.getTableEntries().keySet()) {
            if (catalog_tbl.getSystable()) continue;
            PartitionEntry pentry = pplan.getTableEntries().get(catalog_tbl);
            assertNotNull("Null PartitionEntry for " + catalog_tbl, pentry);
            Collection<Column> pkey_columns = CatalogUtil.getPrimaryKeyColumns(catalog_tbl);
            PartitionMethodType expected = (pkey_columns.isEmpty() ? PartitionMethodType.REPLICATION : PartitionMethodType.HASH);
            assertEquals("Invalid PartitionMethodType for " + catalog_tbl, expected, pentry.getMethod());
        } // FOR
        System.err.println(pplan);
    }