@Test
    public void testDelete() {
        System.out.println("delete");
        Status result = instance.delete(MOCK_TABLE, MOCK_KEY1);
        assertEquals(Status.OK, result);
    }