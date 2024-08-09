private void bug15890LineCount(JFlexXref xref) throws Exception {
        final int EXP_N = 10;
        StringWriter out = new StringWriter();
        xref.write(out);
        if (EXP_N != xref.getLineNumber()) {
            System.out.println(out.toString());
            assertEquals("xref line count", EXP_N, xref.getLineNumber());
        }
    }