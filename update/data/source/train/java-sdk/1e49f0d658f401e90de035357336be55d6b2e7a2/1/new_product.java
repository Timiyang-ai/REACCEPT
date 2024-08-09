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

    Pattern isJustNumber = Pattern.compile("^\\d+$");
    Matcher foundMatch = isJustNumber.matcher(dateAsString);
    if (foundMatch.find()) {
      Long time_t = Long.parseLong(dateAsString);
      Long msCheck = 1000000000000L;

      // are we ms or seconds maybe?
      if (time_t > msCheck) {
        // assuming milliseconds
        time_t = time_t / 1000;
      }
      return new Date(time_t);
    }

    LOG.log(Level.SEVERE, "Error parsing: " + dateAsString, e);
    return null;
  }