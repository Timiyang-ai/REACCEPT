public static SanitizedContent cleanHtml(SoyValue value) {
    return cleanHtml(value, ImmutableSet.of());
  }