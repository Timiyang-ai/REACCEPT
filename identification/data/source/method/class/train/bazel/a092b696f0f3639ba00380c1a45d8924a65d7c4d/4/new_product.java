public static boolean getHasPy3OnlySources(TransitiveInfoCollection target) throws EvalException {
    if (hasModernProvider(target)) {
      return getModernProvider(target).getHasPy3OnlySources();
    } else if (hasLegacyProvider(target)) {
      return PyStructUtils.getHasPy3OnlySources(getLegacyProvider(target));
    } else {
      return false;
    }
  }