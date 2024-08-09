private static void soundexDiff(final String string1, final String string2, final int diff) {
    final Function func = _STRINGS_SOUNDEX;
    // queries
    query("sum(for-each-pair(" +
      "string-to-codepoints(" + func.args(string1) + "), " +
      "string-to-codepoints(" + func.args(string2) + "), " +
      "function($cp1, $cp2) { if($cp1 = $cp2) then 1 else 0 }))", diff);
  }