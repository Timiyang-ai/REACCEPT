  private static final String cleanHtml(String html) {
    return Sanitizers.stripHtmlTags(html, TEST_WHITELIST, true);
  }