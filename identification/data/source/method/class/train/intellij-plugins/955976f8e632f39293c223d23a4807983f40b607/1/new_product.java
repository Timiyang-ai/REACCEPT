public static String unlocalizeFileName(String filename) {

    final int lastDot = filename.lastIndexOf('.');
    final int extension = lastDot >= 0 ? lastDot : filename.length();
    final int lastUnderscore = filename.lastIndexOf('_', extension);
    if (lastUnderscore < 1) return filename;
    String languageOrCountry = filename.substring(lastUnderscore + 1, extension);

    // Last token is the language ?
    if (ALL_LANGUAGES.contains(languageOrCountry)) return removeSubstring(filename, lastUnderscore, extension);

    // Last token is the country ?
    if (!ALL_COUNTRIES.contains(languageOrCountry)) return filename;

    final int nextUnderscore = filename.lastIndexOf('_', lastUnderscore - 1);
    if (nextUnderscore < 1) return filename;
    String language = filename.substring(nextUnderscore + 1, lastUnderscore);
    return ALL_LANGUAGES.contains(language) ? removeSubstring(filename, nextUnderscore, extension) : filename;
  }