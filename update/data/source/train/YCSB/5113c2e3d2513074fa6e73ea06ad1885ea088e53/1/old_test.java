@Test
    public void testInsert() {
        System.out.println("insert");
        int expResult = 0;
        int result = instance.insert(MOCK_TABLE, MOCK_KEY0, MOCK_DATA);
        assertEquals(expResult, result);
    }