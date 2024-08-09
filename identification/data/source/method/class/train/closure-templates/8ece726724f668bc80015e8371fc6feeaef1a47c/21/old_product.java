public static SanitizedContent cleanHtml(SoyValue value) {
    Dir valueDir = null;
    if (value instanceof SanitizedContent) {
      SanitizedContent sanitizedContent = (SanitizedContent) value;
      if (sanitizedContent.getContentKind() == SanitizedContent.ContentKind.HTML) {
        return (SanitizedContent) value;
      }
      valueDir = sanitizedContent.getContentDirection();
    }
    return cleanHtml(value.coerceToString(), valueDir);
  }