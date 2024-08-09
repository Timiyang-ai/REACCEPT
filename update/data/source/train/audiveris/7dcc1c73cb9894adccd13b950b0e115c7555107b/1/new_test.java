@Test
    public void testGetPixel ()
    {
        System.out.println("getPixel");

        int x = 1;
        int y = 0;
        RunsTable instance = createHorizontalInstance();
        int expResult = level;
        int result = instance.getValue(x, y);
        assertEquals(expResult, result);
    }