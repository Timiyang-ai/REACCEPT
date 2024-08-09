@Test
    public void testCopy_0args ()
    {
        System.out.println("\n+++ copy");

        RunTable instance = createHorizontalInstance();
        RunTable expResult = instance;
        RunTable result = instance.copy();

        if (!expResult.isIdentical(result)) {
            fail("Copy not identical to original");
        }
    }