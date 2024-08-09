@Test
    public void testSwapCase_String() {
        assertEquals(null, WordUtils.swapCase(null));
        assertEquals("", WordUtils.swapCase(""));
        assertEquals("  ", WordUtils.swapCase("  "));
        
        assertEquals("i", WordUtils.swapCase("I") );
        assertEquals("I", WordUtils.swapCase("i") );
        assertEquals("I AM HERE 123", WordUtils.swapCase("i am here 123") );
        assertEquals("i aM hERE 123", WordUtils.swapCase("I Am Here 123") );
        assertEquals("I AM here 123", WordUtils.swapCase("i am HERE 123") );
        assertEquals("i am here 123", WordUtils.swapCase("I AM HERE 123") );

        String test = "This String contains a TitleCase character: \u01C8";
        String expect = "tHIS sTRING CONTAINS A tITLEcASE CHARACTER: \u01C9";
        assertEquals(expect, WordUtils.swapCase(test));
    }