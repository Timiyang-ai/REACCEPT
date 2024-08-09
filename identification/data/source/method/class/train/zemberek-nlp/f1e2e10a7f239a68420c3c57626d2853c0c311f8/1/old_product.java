public String formatToCase(WordAnalysis analysis, CaseType type, String apostrophe) {
    String formatted = format(analysis, apostrophe);
    switch (type) {
      case DEFAULT_CASE:
        return formatted;
      case LOWER_CASE:
        return formatted.toLowerCase(Turkish.LOCALE);
      case UPPER_CASE:
        return formatted.toUpperCase(Turkish.LOCALE);
      case TITLE_CASE:
        return Turkish.capitalize(formatted);
      case UPPER_CASE_ROOT_LOWER_CASE_ENDING:
        String ending = analysis.getEnding();
        String lemmaUpper = analysis.getDictionaryItem().lemma.toUpperCase(Turkish.LOCALE);
        if (ending.length() == 0) {
          return lemmaUpper;
        }
        if (apostropheRequired(analysis)) {
          return lemmaUpper + apostrophe + ending;
        } else {
          return lemmaUpper + ending;
        }
      default:
        return "";
    }
  }