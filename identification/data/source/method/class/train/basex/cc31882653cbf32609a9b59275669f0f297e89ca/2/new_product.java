private static void soundexVariations(final String code, final String... variations) {
    final Function func = _STRINGS_SOUNDEX;
    for(final String string : variations) query(func.args(string), code);
  }