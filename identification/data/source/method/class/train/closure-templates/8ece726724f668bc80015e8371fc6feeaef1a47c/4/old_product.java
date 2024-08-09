public static String cleanHtml(String value) {
    return stripHtmlTags(value, TagWhitelist.FORMATTING, true);
  }