public static NestedSet<String> getImports(TransitiveInfoCollection target) throws EvalException {
    if (hasModernProvider(target)) {
      return getModernProvider(target).getImports().getSet(String.class);
    } else if (hasLegacyProvider(target)) {
      return PyStructUtils.getImports(getLegacyProvider(target));
    } else {
      return NestedSetBuilder.emptySet(Order.COMPILE_ORDER);
    }
  }