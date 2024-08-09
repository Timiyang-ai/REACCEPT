    @Test
    void findFile_workingDirectorySecureMode() throws Exception {
        String localFile = new File(".").list()[0];
        try {
            System.setProperty("REDPEN_HOME", "");
            Configuration.builder().secure().build().findFile(localFile);
            fail("Secure mode should not allow files from working directory");
        }
        catch (RedPenException e) {
            assertEquals(localFile + " is not under $REDPEN_HOME (" + new File("").getAbsoluteFile() + ").", e.getMessage());
        }
    }