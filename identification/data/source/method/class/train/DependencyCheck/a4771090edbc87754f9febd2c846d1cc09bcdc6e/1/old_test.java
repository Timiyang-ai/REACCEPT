@Test
    public void testUpdatesNeeded() throws Exception {
        StandardUpdate instance = getStandardUpdateTask();
        Updateable result = instance.updatesNeeded();
        assertNotNull(result);
    }