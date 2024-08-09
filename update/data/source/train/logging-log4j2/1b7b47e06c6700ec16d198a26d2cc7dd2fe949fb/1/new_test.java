@Deprecated
    @Test
    public void testGetAllPossiblePriorities() {
        Priority[] priorities = Priority.getAllPossiblePriorities();
        assertEquals(5, priorities.length);
    }