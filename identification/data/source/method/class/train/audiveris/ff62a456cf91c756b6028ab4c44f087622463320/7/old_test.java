@Test
    public void testGetRunAt ()
    {
        System.out.println("getRunAt");

        RunTable instance = createHorizontalInstance();
        Run result = instance.getRunAt(0, 0);
        Run expResult = null;
        assertEquals(expResult, result);

        result = instance.getRunAt(6, 0);
        expResult = new Run(5, 3);
        assertEquals(expResult.toString(), result.toString());
    }