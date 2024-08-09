public static MediaType parse(String string) {
    Matcher typeSubtype = TYPE_SUBTYPE.matcher(string);
    if (!typeSubtype.lookingAt()) return null;
    String type = typeSubtype.group(1).toLowerCase(Locale.US);
    String subtype = typeSubtype.group(2).toLowerCase(Locale.US);

    String charset = null;
    Matcher parameter = PARAMETER.matcher(string);
    for (int s = typeSubtype.end(); s < string.length(); s = parameter.end()) {
      parameter.region(s, string.length());
      if (!parameter.lookingAt()) return null; // This is not a well-formed media type.

      String name = parameter.group(1);
      if (name == null || !name.equalsIgnoreCase("charset")) continue;
      String charsetParameter = parameter.group(2) != null
          ? parameter.group(2)  // Value is a token.
          : parameter.group(3); // Value is a quoted string.
      if (charset != null && !charsetParameter.equalsIgnoreCase(charset)) {
        throw new IllegalArgumentException("Multiple different charsets: " + string);
      }
      charset = charsetParameter;
    }

    return new MediaType(string, type, subtype, charset);
  }