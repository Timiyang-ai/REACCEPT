@Test
    public void testParse() throws Exception {
        System.out.println("parse");

        String[] args = {};
        PrintStream out = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        CliParser instance = new CliParser();
        instance.parse(args);

        assertFalse(instance.isGetVersion());
        assertFalse(instance.isGetHelp());
        assertFalse(instance.isRunScan());
    }