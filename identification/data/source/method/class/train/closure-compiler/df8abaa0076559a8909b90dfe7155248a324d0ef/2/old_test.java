  private Node parse(String[] original) {
    String[] argStrings = args.toArray(new String[] {});
    CommandLineRunner runner = new CommandLineRunner(argStrings);
    Compiler compiler = runner.createCompiler();
    List<SourceFile> inputs = new ArrayList<>();
    for (int i = 0; i < original.length; i++) {
      inputs.add(SourceFile.fromCode(getFilename(i), original[i]));
    }
    CompilerOptions options = runner.createOptions();
    compiler.init(externs, inputs, options);
    Node all = compiler.parseInputs();
    assertThat(compiler.getErrors()).isEmpty();
    checkNotNull(all);
    Node n = all.getLastChild();
    return n;
  }