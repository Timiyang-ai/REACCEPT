    @Test
    public void initialize_withOptions() {
        this.options.put("debug", "true");
        this.options.put("principalFormat", "sid");
        this.options.put("roleFormat", "none");
        this.options.put("junk", "junk");
        this.loginModule.initialize(this.subject, this.callbackHandler, null, this.options);
        Assertions.assertTrue(this.loginModule.isDebug());
        Assertions.assertEquals(PrincipalFormat.SID, Whitebox.getInternalState(this.loginModule, "principalFormat"));
        Assertions.assertEquals(PrincipalFormat.NONE, Whitebox.getInternalState(this.loginModule, "roleFormat"));
    }