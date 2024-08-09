    @Test
    public void compilationConstantTest() {
        FrameDescriptor descriptor = new FrameDescriptor();
        CompilationConstantTestNode result = new CompilationConstantTestNode(new ConstantTestNode(5));
        RootTestNode rootNode = new RootTestNode(descriptor, "compilationConstant", result);
        compileHelper("compilationConstant", rootNode, new Object[0]);
    }