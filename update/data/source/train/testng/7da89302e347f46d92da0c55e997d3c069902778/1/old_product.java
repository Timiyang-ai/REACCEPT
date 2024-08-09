protected static ITestNGMethod[] findDependedUponMethods(ITestNGMethod m, List<ITestNGMethod> methods) {
    ITestNGMethod[] methodsArray = methods.toArray(new ITestNGMethod[methods.size()]);
    return findDependedUponMethods(m, methodsArray);
  }