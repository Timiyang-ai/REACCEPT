@Test
    public void testInsert() {
        System.out.println("insert");
        Status result = instance.insert(MOCK_TABLE, MOCK_KEY0, MOCK_DATA);
        assertEquals(Status.OK, result);
    }