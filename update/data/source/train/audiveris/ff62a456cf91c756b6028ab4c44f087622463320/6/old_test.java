@Test
    public void testGetTotalRunCount ()
    {
        System.out.println("getTotalRunCount");

        RunTable instance = createHorizontalInstance();
        int expResult = 11;
        int result = instance.getTotalRunCount();
        assertEquals(expResult, result);

        instance = createVerticalInstance();
        expResult = 19;
        result = instance.getTotalRunCount();
        assertEquals(expResult, result);
    }