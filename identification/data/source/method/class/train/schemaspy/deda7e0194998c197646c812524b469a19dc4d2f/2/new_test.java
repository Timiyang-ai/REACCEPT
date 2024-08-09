    @Test
    @Logger(CommandLineArgumentParser.class)
    public void printUsage() {
        String[] args = {
                "-o", "aFolder",
                "-sso"
        };
        CommandLineArgumentParser parser = new CommandLineArgumentParser(new CommandLineArguments(), NO_DEFAULT_PROVIDER);

        CommandLineArguments arguments = parser.parse(args);
        parser.printUsage();
        assertThat(loggingRule.getLog()).contains("Options:");
    }