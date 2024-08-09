@Test
    public void testGetDirectory() throws Exception {
        System.out.println("getDirectory");
        Directory result = Index.getDirectory();
        String exp = "\\target\\store\\cpe";
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(result.toString().contains(exp));
    }