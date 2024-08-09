@Test
    public void testGetWorkflowGroup() {
        //null by default
        int step = 1;
        assertThat("testGetWorkflowGroup 0", collectionService.getWorkflowGroup(context, collection, step),
                nullValue());
    }