  public boolean doesFunctionMeetMinimumRequirements(final String code, final String fnName) {
    final Compiler compiler = new Compiler();
    compiler.initOptions(new CompilerOptions());
    final FunctionInjector injector =
        new FunctionInjector.Builder(compiler)
            .allowDecomposition(allowDecomposition)
            .allowMethodCallDecomposing(allowMethodCallDecomposition)
            .assumeStrictThis(assumeStrictThis)
            .assumeMinimumCapture(assumeMinimumCapture)
            .build();
    final Node tree = parse(compiler, code);

    final Node fnNode = findFunction(tree, fnName);
    return injector.doesFunctionMeetMinimumRequirements(fnName, fnNode);
  }