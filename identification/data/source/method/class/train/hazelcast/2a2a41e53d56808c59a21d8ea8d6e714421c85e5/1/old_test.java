    @Test(expected = NullPointerException.class)
    public void register_whenNullPlugin() throws Exception {
        Diagnostics diagnostics = newDiagnostics(new Config().setProperty(Diagnostics.ENABLED.getName(), "true"));
        diagnostics.start();
        diagnostics.register(null);
    }