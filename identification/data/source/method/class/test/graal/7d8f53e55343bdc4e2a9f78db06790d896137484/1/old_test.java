    @Test
    @SuppressWarnings("try")
    public void neverPartOfCompilationTest() {
        NeverPartOfCompilationTestNode result = new NeverPartOfCompilationTestNode();
        RootTestNode rootNode = new RootTestNode(new FrameDescriptor(), "neverPartOfCompilation", result);
        try (PreventDumping noDump = new PreventDumping()) {
            compileHelper("neverPartOfCompilation", rootNode, new Object[0]);
            Assert.fail("Expected bailout exception due to never part of compilation");
        } catch (BailoutException e) {
            // Bailout exception expected.
        }
    }