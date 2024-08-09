public static NestedSet<String> getImports(TransitiveInfoCollection target) throws EvalException {
    PythonImportsProvider importsProvider = target.getProvider(PythonImportsProvider.class);
    if (importsProvider != null) {
      return importsProvider.getTransitivePythonImports();
    } else {
      return NestedSetBuilder.emptySet(Order.COMPILE_ORDER);
    }
  }