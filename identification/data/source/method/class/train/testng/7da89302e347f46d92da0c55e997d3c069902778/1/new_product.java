protected static ITestNGMethod[] findDependedUponMethods(ITestNGMethod m, List<ITestNGMethod> methods) {
    ITestNGMethod[] methodsArray = methods.toArray(new ITestNGMethod[0]);
    return findDependedUponMethods(m, methodsArray);
  }