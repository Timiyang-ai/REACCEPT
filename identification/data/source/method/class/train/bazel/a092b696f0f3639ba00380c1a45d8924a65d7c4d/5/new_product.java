public static boolean getHasPy2OnlySources(TransitiveInfoCollection target) throws EvalException {
    if (hasModernProvider(target)) {
      return getModernProvider(target).getHasPy2OnlySources();
    } else if (hasLegacyProvider(target)) {
      return PyStructUtils.getHasPy2OnlySources(getLegacyProvider(target));
    } else {
      return false;
    }
  }