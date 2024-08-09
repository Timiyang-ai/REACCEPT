@Override
  public synchronized Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

    if (json.isJsonNull() || json.getAsString().isEmpty()) {
      return null;
    }

    String dateAsString = json.getAsJsonPrimitive().getAsString().replaceAll("Z$", "+0000");
    ParseException e = null;

    for (SimpleDateFormat format : dateFormatters) {
      try {
        return format.parse(dateAsString);
      } catch (ParseException e1) {
        e = e1;
      }
    }

    LOG.log(Level.SEVERE, "Error parsing: " + dateAsString, e);
    return null;
  }