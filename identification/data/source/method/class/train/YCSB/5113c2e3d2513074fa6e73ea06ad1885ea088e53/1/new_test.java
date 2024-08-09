@Test
    public void testRead() {
        System.out.println("read");
        Set<String> fields = MOCK_DATA.keySet();
        HashMap<String, ByteIterator> resultParam = new HashMap<String, ByteIterator>(10);
        Status result = instance.read(MOCK_TABLE, MOCK_KEY1, fields, resultParam);
        assertEquals(Status.OK, result);
    }