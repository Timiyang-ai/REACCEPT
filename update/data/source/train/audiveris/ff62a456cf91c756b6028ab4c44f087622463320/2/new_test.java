@Test
    public void testIsIdentical ()
    {
        System.out.println("\n+++ isIdentical");

        RunTable that = createHorizontalInstance();
        RunTable instance = createHorizontalInstance();
        boolean expResult = true;
        boolean result = instance.isIdentical(that);
        assertEquals(expResult, result);

        that = createVerticalInstance();
        expResult = false;
        result = instance.isIdentical(that);
        assertEquals(expResult, result);
    }