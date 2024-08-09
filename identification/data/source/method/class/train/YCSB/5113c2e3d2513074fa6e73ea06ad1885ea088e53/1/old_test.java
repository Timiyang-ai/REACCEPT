@Test
    public void testRead() {
        System.out.println("read");
        Set<String> fields = MOCK_DATA.keySet();
        HashMap<String, ByteIterator> resultParam = new HashMap<String, ByteIterator>(10);
        int expResult = 0;
        int result = instance.read(MOCK_TABLE, MOCK_KEY1, fields, resultParam);
        assertEquals(expResult, result);
    }