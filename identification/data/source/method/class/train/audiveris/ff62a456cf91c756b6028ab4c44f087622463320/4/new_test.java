@Test
    public void testLookupRun ()
    {
        System.out.println("\n+++ lookupRun");

        RunTable instance = createHorizontalInstance();
        Point point = new Point(6, 0);
        Run expResult = new Run(5, 3);
        Run result = instance.lookupRun(point);
        assertEquals(expResult.toString(), result.toString());

        point = new Point(7, 1);
        result = instance.lookupRun(point);
        assertEquals(null, result);
    }