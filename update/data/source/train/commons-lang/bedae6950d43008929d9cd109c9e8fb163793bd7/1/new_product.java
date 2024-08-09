public static String getAbbreviatedName(final Class<?> cls, final int lengthHint) {
      if (cls == null) {
        return StringUtils.EMPTY;
      }
      return getAbbreviatedName(cls.getName(), lengthHint);
    }