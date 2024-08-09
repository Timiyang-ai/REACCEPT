@Test
    public void testParse_scan_unknownFile() throws Exception {

        String[] args = {"-scan", "jar.that.does.not.exist", "--project", "test"};

        CliParser instance = new CliParser(getSettings());
        try {
            instance.parse(args);
            Assert.fail("An exception should have been thrown");
        } catch (FileNotFoundException ex) {
            Assert.assertTrue(ex.getMessage().contains("Invalid 'scan' argument"));
        }

        Assert.assertFalse(instance.isGetVersion());
        Assert.assertFalse(instance.isGetHelp());
        Assert.assertFalse(instance.isRunScan());
    }