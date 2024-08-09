    @SuppressWarnings("deprecation")
    @Test
    public void testCommandLine_isUsageHelpRequested_trueWhenSpecified() {
        List<CommandLine> parsedCommands = new CommandLine(new RequiredField()).parse("--help");
        assertTrue("usage help requested", parsedCommands.get(0).isUsageHelpRequested());
    }