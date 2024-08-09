@Test
    public void testInitialize() throws Exception {
        ArchiveAnalyzer instance = new ArchiveAnalyzer();
        instance.initialize();

        instance.close();

        //no exception means things worked.
    }