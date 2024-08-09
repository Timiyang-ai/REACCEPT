public static String getAbbreviatedName(final Class<?> cls, int len) {
      if (cls == null) {
        return StringUtils.EMPTY;
      }
      return getAbbreviatedName(cls.getName(), len);
    }