public String formatToCase(SingleAnalysis analysis, CaseType type, String apostrophe) {
    String formatted = format(analysis, apostrophe);
    Locale locale = analysis.getDictionaryItem().hasAttribute(RootAttribute.LocaleEn) ?
        Locale.ENGLISH : Turkish.LOCALE;
    switch (type) {
      case DEFAULT_CASE:
        return formatted;
      case LOWER_CASE:
        return formatted.toLowerCase(locale);
      case UPPER_CASE:
        return formatted.toUpperCase(locale);
      case TITLE_CASE:
        return Turkish.capitalize(formatted);
      case UPPER_CASE_ROOT_LOWER_CASE_ENDING:
        String ending = analysis.getEnding();
        String lemmaUpper = analysis.getDictionaryItem().normalizedLemma().toUpperCase(locale);
        if (ending.length() == 0) {
          return lemmaUpper;
        }
        if (apostrophe != null || apostropheRequired(analysis)) {
          if (apostrophe == null) {
            apostrophe = "'";
          }
          return lemmaUpper + apostrophe + ending;
        } else {
          return lemmaUpper + ending;
        }
      default:
        return "";
    }
  }