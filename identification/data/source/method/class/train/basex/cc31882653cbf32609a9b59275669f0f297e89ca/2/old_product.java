private static void soundexVariations(final String code, final String... variations) {
    for(final String string : variations) query(_STRINGS_SOUNDEX.args(string), code);
  }