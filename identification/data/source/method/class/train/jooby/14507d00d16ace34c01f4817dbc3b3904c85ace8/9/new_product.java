public static MediaType valueOf(final String type) {
    requireNonNull(type, "A mediaType is required.");
    MediaType aliastype = alias.get(type.trim());
    if (aliastype != null) {
      return aliastype;
    }
    String[] parts = type.trim().split(";");
    String[] typeAndSubtype = parts[0].split("/");
    checkArgument(typeAndSubtype.length == 2, "Bad media type: %s", type);
    String stype = typeAndSubtype[0].trim();
    String subtype = typeAndSubtype[1].trim();
    checkArgument(!(stype.equals("*") && !subtype.equals("*")), "Bad media type: %s", type);
    Map<String, String> parameters = DEFAULT_PARAMS;
    if (parts.length > 1) {
      parameters = new LinkedHashMap<>(DEFAULT_PARAMS);
      for (int i = 1; i < parts.length; i++) {
        String[] parameter = parts[i].split("=");
        if (parameter.length > 1) {
          parameters.put(parameter[0].trim(), parameter[1].trim().toLowerCase());
        }
      }
    }
    return new MediaType(stype, subtype, parameters);
  }