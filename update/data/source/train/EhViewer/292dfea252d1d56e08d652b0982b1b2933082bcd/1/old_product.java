public static String getCategory(int type) {
    int i, n;
    for (i = 0, n = CATEGORY_VALUES.length - 1; i < n; ++i) {
      if (CATEGORY_VALUES[i] == type) break;
    }
    return CATEGORY_STRINGS[i][0];
  }