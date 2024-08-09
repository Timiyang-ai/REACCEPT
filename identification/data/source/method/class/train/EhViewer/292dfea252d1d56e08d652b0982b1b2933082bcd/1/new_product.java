@Nullable
  public static String getCategory(int type) {
    for (int i = 0, n = CATEGORY_VALUES.length - 1; i < n; ++i) {
      if (CATEGORY_VALUES[i] == type) {
        return CATEGORY_STRINGS[i][0];
      }
    }
    return null;
  }