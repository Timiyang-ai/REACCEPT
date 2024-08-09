@Test
    public void testStripAllTags() {

        System.out.println("stripAllTags");
        String unsafe = "";
        String expResult = "";
        String result = MarkupChecker.stripAllTags(unsafe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

        //test null
        unsafe = null;
        result = MarkupChecker.stripAllTags(unsafe);
        assertNull(result);

        unsafe = "Johnson & Johnson <>";
        expResult = "Johnson & Johnson <>";
        result = MarkupChecker.stripAllTags(unsafe);
        assertEquals(expResult, result);

        unsafe = "Johnson && Johnson <&>&";
        expResult = "Johnson && Johnson <&>&";
        result = MarkupChecker.stripAllTags(unsafe);
        assertEquals(expResult, result);

    }