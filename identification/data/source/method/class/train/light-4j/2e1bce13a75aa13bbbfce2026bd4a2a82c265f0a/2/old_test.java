    @Test
    public void validate_Path() {
        PathChain chain = new PathChain();
        chain.setPath("/my/path");
        chain.setMethod("GET");
        chain.validate("unit test config");
    }