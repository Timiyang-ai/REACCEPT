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

        bug15890LineCount(new JFlexXref(new CXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new CxxXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new LispXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new JavaXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new ScalaXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new FortranXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new HaskellXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new XMLXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new ShXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new TclXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new SQLXref(new StringReader(fileContents))));
        bug15890LineCount(new TroffXref(new StringReader(fileContents)));
        bug15890LineCount(new JFlexXref(new PlainXref(new StringReader(fileContents))));
        bug15890LineCount(new JFlexXref(new PerlXref(new StringReader(fileContents))));
    }