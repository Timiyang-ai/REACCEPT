@Test
    public void testUpdate() {
        System.out.println("update");
        int i;
        HashMap<String, ByteIterator> newValues = new HashMap<String, ByteIterator>(10);

        for (i = 1; i <= 10; i++) {
            newValues.put("field" + i, new StringByteIterator("newvalue" + i));
        }

        int expResult = 0;
        int result = instance.update(MOCK_TABLE, MOCK_KEY1, newValues);
        assertEquals(expResult, result);

        //validate that the values changed
        HashMap<String, ByteIterator> resultParam = new HashMap<String, ByteIterator>(10);
        instance.read(MOCK_TABLE, MOCK_KEY1, MOCK_DATA.keySet(), resultParam);

        for (i = 1; i <= 10; i++) {
            assertEquals("newvalue" + i, resultParam.get("field" + i).toString());
        }

    }