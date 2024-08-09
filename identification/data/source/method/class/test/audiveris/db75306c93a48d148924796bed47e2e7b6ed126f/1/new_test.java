@Test
    public void testCopy_String ()
    {
        System.out.println("\n+++ copy");

        RunTable instance = createHorizontalInstance();
        RunTable expResult = createHorizontalInstance();
        RunTable result = instance.copy();

        if (!expResult.equals(result)) {
            fail("Copy not identical to original");
        }
    }