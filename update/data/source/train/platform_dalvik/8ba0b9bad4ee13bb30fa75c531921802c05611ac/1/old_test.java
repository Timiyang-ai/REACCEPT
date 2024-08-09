@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "clone",
        args = {}
    )
    public void test_clone() {
        try {
            // case 1: Compare of internal variables of cloned objects
            DecimalFormatSymbols fs = new DecimalFormatSymbols(Locale.US);
            DecimalFormatSymbols fsc = (DecimalFormatSymbols) fs.clone();
            assertEquals(fs.getCurrency(), fsc.getCurrency());

            // case 2: Compare of clones
            fs = new DecimalFormatSymbols();
            DecimalFormatSymbols fsc2 = (DecimalFormatSymbols) (fs.clone());
            // make sure the objects are equal
            assertTrue("Object's clone isn't equal!", fs.equals(fsc2));

            // case 3:
            // change the content of the clone and make sure it's not equal
            // anymore
            // verifies that it's data is now distinct from the original
            fs.setNaN("not-a-number");
            assertTrue("Object's changed clone should not be equal!", !fs
                    .equals(fsc2));
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString());
        }
    }