public static String getImplName(String implName) {
    if (isDesignTime()) {
      implName += "_designTime" + System.currentTimeMillis();
    }
    return implName;
  }