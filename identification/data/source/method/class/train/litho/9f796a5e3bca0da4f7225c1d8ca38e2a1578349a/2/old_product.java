static ClassName guessClassName(String typeName) {
    // ClassName#bestGuess throws an error for a not fully-qualified class name string.
    // For the sake of Litho model verification, we do type simplification for non-standard cases.
    typeName = typeName.trim();
    if (typeName.length() < 2) {
      return ClassName.bestGuess(Object.class.getTypeName());
    }
    if (typeName.contains("<")) {
      return guessClassName(typeName.substring(0, typeName.indexOf("<")));
    }
    String extendsWord = "extends ";
    if (typeName.contains(extendsWord)) {
      return guessClassName(
          typeName.substring(typeName.indexOf(extendsWord) + extendsWord.length()));
    }
    String andWord = " &";
    if (typeName.contains(andWord)) {
      return guessClassName(typeName.substring(0, typeName.indexOf(andWord)));
    }
    return ClassName.bestGuess(typeName);
  }