@Test
    public void testBug15890LineCount() throws Exception {
        String fileContents =
                "line 1\n" +
                "line 2\n" +
                "line 3\n" +
                "line 4 with \u000B char\n" +
                "line 5 with \u000C char\n" +
                "line 6 with \u0085 char\n" +
                "line 7 with \u2028 char\n" +
                "line 8 with \u2029 char\n" +
                "line 9\n";

        bug15890LineCount(new CXref(new StringReader(fileContents)));
        bug15890LineCount(new CxxXref(new StringReader(fileContents)));
        bug15890LineCount(new LispXref(new StringReader(fileContents)));
        bug15890LineCount(new JavaXref(new StringReader(fileContents)));
        bug15890LineCount(new ScalaXref(new StringReader(fileContents)));
        bug15890LineCount(new FortranXref(new StringReader(fileContents)));
        bug15890LineCount(new HaskellXref(new StringReader(fileContents)));
        bug15890LineCount(new XMLXref(new StringReader(fileContents)));
        bug15890LineCount(new ShXref(new StringReader(fileContents)));
        bug15890LineCount(new TclXref(new StringReader(fileContents)));
        bug15890LineCount(new SQLXref(new StringReader(fileContents)));
        bug15890LineCount(new TroffXref(new StringReader(fileContents)));
        bug15890LineCount(new PlainXref(new StringReader(fileContents)));
        bug15890LineCount(new PerlXref(new StringReader(fileContents)));
    }