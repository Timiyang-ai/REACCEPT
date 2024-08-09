void addOneTimePass(PassFactory factory) {
    passes.add(new PassFactoryDelegate(compiler, factory));
  }