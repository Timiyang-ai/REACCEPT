@Test
    public void testDelete() {
        System.out.println("delete");
        int expResult = 0;
        int result = instance.delete(MOCK_TABLE, MOCK_KEY1);
        assertEquals(expResult, result);
    }