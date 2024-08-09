@Test
    public void testGetDirectory() throws Exception {
        System.out.println("getDirectory");
        Index index = new Index();
        Directory result = index.getDirectory();
        String exp = "\\target\\store\\cpe";
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(result.toString().contains(exp));
    }