public static String getRuleLanguage(String ruleClass) {
    int index = ruleClass.lastIndexOf('_');
    // Chop off "_binary" or "_test".
    return index != -1 ? ruleClass.substring(0, index) : ruleClass;
  }