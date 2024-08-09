@Test
    public void testCopy_String ()
    {
        System.out.println("\n+++ copy");

        RunTable instance = createHorizontalInstance();
        RunTable expResult = createHorizontalInstance();
        RunTable result = instance.copy("hori");

        if (!expResult.isIdentical(result) || !expResult.getName().equals(result.getName())) {
            fail("Copy not identical to original");
        }
    }