@Test
    public void testValidate() throws Exception {
        System.out.println("test LocalInstanceCommand.validate");
        try {
            nodeDir = nodeAgentsDir.getAbsolutePath();
            instanceName = "i1";
            isCreateInstanceFilesystem = true;
            validate();
        }
        catch(CommandException e) {
            fail("validate failed!!!");
            throw e;
        }
    }