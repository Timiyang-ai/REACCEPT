@Test
    public void testClone ()
            throws Exception
    {
        System.out.println("clone");

        RunsTable instance = createHorizontalInstance();
        RunsTable expResult = instance;
        RunsTable result = instance.copy();

        if (!expResult.isIdentical(result)) {
            fail("Clone not identical to original");
        }
    }