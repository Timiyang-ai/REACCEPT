@Test
    public void testGetDirectory() throws Exception {
        System.out.println("getDirectory");
        Index instance = new Index();
        String exp = File.separatorChar + "target" + File.separatorChar + "data" + File.separatorChar + "cve";
        Directory result = instance.getDirectory();
        
        assertTrue("Recieved '" + result.toString() + "' and excpected '" + exp + "'.",
                result.toString().contains(exp));
    }