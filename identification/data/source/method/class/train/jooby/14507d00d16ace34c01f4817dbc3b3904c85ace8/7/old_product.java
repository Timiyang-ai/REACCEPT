public static MediaType valueOf(final String mediaType) {
    requireNonNull(mediaType, "A mediaType is required.");
    String[] parts = mediaType.split(";");
    checkArgument(parts.length > 0, "Bad media type: %s", mediaType);
    String[] typeAndSubtype = (parts[0].equals("*") ? "*/*" : parts[0]).split("/");
    checkArgument(typeAndSubtype.length == 2, "Bad media type: %s", mediaType);
    String type = typeAndSubtype[0].trim();
    String subtype = typeAndSubtype[1].trim();
    checkArgument(!(type.equals("*") && !subtype.equals("*")), "Bad media type: %s", mediaType);
    Map<String, String> parameters = DEFAULT_PARAMS;
    if (parts.length > 1) {
      parameters = new LinkedHashMap<>(DEFAULT_PARAMS);
      for (int i = 1; i < parts.length; i++) {
        String[] parameter = parts[i].split("=");
        if (parameter.length > 1) {
          parameters.put(parameter[0].trim(), parameter[1].trim());
        }
      }
    }
    return new MediaType(type, subtype, parameters);
  }