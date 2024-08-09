@Test
    public void evalTest() {
        final Options options = new Options("");
        final ErrorManager errors = new ErrorManager();
        final Context cx = new Context(options, errors, Thread.currentThread().getContextClassLoader());
        final ScriptObject oldGlobal = Context.getGlobal();
        Context.setGlobal(cx.createGlobal());
        try {
            String code = "22 + 10";
            assertTrue(32.0 == ((Number)(eval(cx, "<evalTest>", code))).doubleValue());

            code = "obj = { js: 'nashorn' }; obj.js";
            assertEquals(eval(cx, "<evalTest2>", code), "nashorn");
        } finally {
            Context.setGlobal(oldGlobal);
        }
    }