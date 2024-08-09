public static PythonVersion parseSrcsValue(String str) {
    if (!SRCS_STRINGS.contains(str)) {
      throw new IllegalArgumentException(
          String.format("'%s' is not a valid Python srcs_version value.", str));
    }
    return PythonVersion.valueOf(str);
  }