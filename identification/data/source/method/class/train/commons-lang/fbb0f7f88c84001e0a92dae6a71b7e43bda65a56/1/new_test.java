@Test
    public void testWrap_StringInt() {
        assertEquals(null, WordUtils.wrap(null, 20));
        assertEquals(null, WordUtils.wrap(null, -1));
        
        assertEquals("", WordUtils.wrap("", 20));
        assertEquals("", WordUtils.wrap("", -1));
        
        // normal
        final String systemNewLine = System.getProperty("line.separator");
        String input = "Here is one line of text that is going to be wrapped after 20 columns.";
        String expected = "Here is one line of" + systemNewLine + "text that is going" 
            + systemNewLine + "to be wrapped after" + systemNewLine + "20 columns.";
        assertEquals(expected, WordUtils.wrap(input, 20));
        
        // long word at end
        input = "Click here to jump to the commons website - http://commons.apache.org";
        expected = "Click here to jump" + systemNewLine + "to the commons" + systemNewLine 
            + "website -" + systemNewLine + "http://commons.apache.org";
        assertEquals(expected, WordUtils.wrap(input, 20));
        
        // long word in middle
        input = "Click here, http://commons.apache.org, to jump to the commons website";
        expected = "Click here," + systemNewLine + "http://commons.apache.org," + systemNewLine 
            + "to jump to the" + systemNewLine + "commons website";
        assertEquals(expected, WordUtils.wrap(input, 20));

        // leading spaces on a new line are stripped
        // trailing spaces are not stripped
        input = "word1             word2                        word3";
        expected = "word1  " + systemNewLine + "word2  " + systemNewLine + "word3";
        assertEquals(expected, WordUtils.wrap(input, 7));
    }