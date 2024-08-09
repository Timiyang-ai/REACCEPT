@Test
    public void testGetTotalRunCount ()
    {
        System.out.println("\n+++ getTotalRunCount");

        RunTable instance = createHorizontalInstance();
        int expResult = 9;
        int result = instance.getTotalRunCount();
        assertEquals(expResult, result);

        instance = createVerticalInstance();
        expResult = 16;
        result = instance.getTotalRunCount();
        assertEquals(expResult, result);
    }