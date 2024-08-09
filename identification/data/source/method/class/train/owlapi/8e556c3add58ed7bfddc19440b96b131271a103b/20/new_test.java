    @Test
    public void parseTestFile1() throws IOException {
        File oboFile =
            new File(this.getClass().getClassLoader().getResource(FILE_TEST_NAME1).getFile());
        assertNotNull(oboFile);
        OBOFormatParser parser = new OBOFormatParser();
        OBODoc oboDoc = parser.parse(oboFile);
        assertNotNull(oboDoc);
        oboDoc.check();
    }