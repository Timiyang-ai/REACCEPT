@Test
    public void testUpdatesNeeded() throws Exception {
        StandardUpdate instance = getStandardUpdateTask();
        UpdateableNvdCve result = instance.updatesNeeded();
        assertNotNull(result);
    }