@Test
    public void testInitialize() throws Exception {
        ArchiveAnalyzer instance = new ArchiveAnalyzer();
        instance.setEnabled(true);
        instance.setFilesMatched(true);
        instance.initialize();

        instance.close();

        //no exception means things worked.
    }