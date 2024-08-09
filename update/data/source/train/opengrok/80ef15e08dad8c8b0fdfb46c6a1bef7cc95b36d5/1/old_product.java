private void bug15890LineCount(JFlexXref xref) throws Exception {
        xref.write(new StringWriter());
        assertEquals(10, xref.getLineNumber());
    }