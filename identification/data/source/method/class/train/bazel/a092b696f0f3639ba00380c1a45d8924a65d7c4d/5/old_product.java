public static boolean getHasPy2OnlySources(TransitiveInfoCollection target) throws EvalException {
    if (hasProvider(target)) {
      return PyStructUtils.getHasPy2OnlySources(getProvider(target));
    } else {
      return false;
    }
  }