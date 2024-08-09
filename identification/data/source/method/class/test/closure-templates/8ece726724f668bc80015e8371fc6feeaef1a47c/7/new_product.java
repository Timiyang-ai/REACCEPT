public static SanitizedContent cleanHtml(
      String value, Dir contentDir, Collection<? extends OptionalSafeTag> optionalSafeTags) {
    return UnsafeSanitizedContentOrdainer.ordainAsSafe(
        stripHtmlTags(value, TagWhitelist.FORMATTING.withOptionalSafeTags(optionalSafeTags), true),
        ContentKind.HTML,
        contentDir);
  }