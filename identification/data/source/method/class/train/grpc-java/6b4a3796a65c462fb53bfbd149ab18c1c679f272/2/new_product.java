@VisibleForTesting
  static List<Map<String, ?>> parseTxtResults(List<String> txtRecords) throws IOException {
    List<Map<String, ?>> possibleServiceConfigChoices = new ArrayList<>();
    for (String txtRecord : txtRecords) {
      if (!txtRecord.startsWith(SERVICE_CONFIG_PREFIX)) {
        logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{txtRecord});
        continue;
      }
      Object rawChoices = JsonParser.parse(txtRecord.substring(SERVICE_CONFIG_PREFIX.length()));
      if (!(rawChoices instanceof List)) {
        throw new ClassCastException("wrong type " + rawChoices);
      }
      List<?> listChoices = (List<?>) rawChoices;
      possibleServiceConfigChoices.addAll(JsonUtil.checkObjectList(listChoices));
    }
    return possibleServiceConfigChoices;
  }