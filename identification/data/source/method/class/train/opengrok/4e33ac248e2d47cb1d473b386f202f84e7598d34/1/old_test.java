@Test
    public void testMain() throws IOException {
        System.out.println("Generate index by using command line options");
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        env.setCtags(System.getProperty("org.opensolaris.opengrok.configuration.ctags", "ctags"));
        if (env.validateExuberantCtags()) {
            String[] argv = { "-S", "-P", "-p", "/c", "-H", "-Q", "off", "-s", repository.getSourceRoot().getAbsolutePath(), "-d", repository.getDataRoot().getAbsolutePath(), "-v"};
            Indexer.main(argv);
        } else {
            System.out.println("Skipping test. Could not find a ctags I could use in path.");
        }
    }