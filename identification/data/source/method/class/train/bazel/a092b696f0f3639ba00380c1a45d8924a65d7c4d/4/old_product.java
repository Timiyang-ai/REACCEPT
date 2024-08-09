public static boolean getHasPy3OnlySources(TransitiveInfoCollection target) throws EvalException {
    if (hasProvider(target)) {
      return PyStructUtils.getHasPy3OnlySources(getProvider(target));
    } else {
      return false;
    }
  }