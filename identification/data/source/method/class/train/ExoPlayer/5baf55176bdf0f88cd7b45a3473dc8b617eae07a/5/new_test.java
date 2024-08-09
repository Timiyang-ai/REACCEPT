  private static Spanned parseCueText(String string) {
    WebvttCue.Builder builder = new WebvttCue.Builder();
    WebvttCueParser.parseCueText(null, string, builder, Collections.emptyList());
    return (Spanned) builder.build().text;
  }