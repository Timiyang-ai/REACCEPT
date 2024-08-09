private static void soundexDifference(final String string1, final String string2,
      final int diff) {

    query("sum(for-each-pair(" +
      "string-to-codepoints(" + _STRINGS_SOUNDEX.args(string1) + "), " +
      "string-to-codepoints(" + _STRINGS_SOUNDEX.args(string2) + "), " +
      "function($cp1, $cp2) { if($cp1 = $cp2) then 1 else 0 }))", diff);
  }