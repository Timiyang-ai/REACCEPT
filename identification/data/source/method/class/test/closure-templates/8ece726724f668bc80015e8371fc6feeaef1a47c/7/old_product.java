public static SanitizedContent cleanHtml(String value, Dir contentDir) {
    return UnsafeSanitizedContentOrdainer.ordainAsSafe(
        stripHtmlTags(value, TagWhitelist.FORMATTING, true), ContentKind.HTML, contentDir);
  }