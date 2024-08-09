public static String getAbbreviatedName(final Class<?> cls, final int len) {
      if (cls == null) {
        return StringUtils.EMPTY;
      }
      return getAbbreviatedName(cls.getName(), len);
    }