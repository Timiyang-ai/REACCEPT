@Test
    public void testGetChecksum_FileNotFound() throws Exception {
        System.out.println("getChecksum (invalid path)");
        String algorithm = "MD5";
        File file = new File("not a valid file");
        boolean exceptionThrown = false;
        try {
            byte[] result = Checksum.getChecksum(algorithm, file);
        } catch (IOException ex) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }