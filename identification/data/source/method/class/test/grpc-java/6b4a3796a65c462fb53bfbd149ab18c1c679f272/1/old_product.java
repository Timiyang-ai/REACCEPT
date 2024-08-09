@SuppressWarnings("unchecked")
  @VisibleForTesting
  static List<Map<String, ?>> parseTxtResults(List<String> txtRecords) {
    List<Map<String, ?>> serviceConfigs = new ArrayList<>();
    for (String txtRecord : txtRecords) {
      if (txtRecord.startsWith(SERVICE_CONFIG_PREFIX)) {
        List<Map<String, ?>> choices;
        try {
          Object rawChoices = JsonParser.parse(txtRecord.substring(SERVICE_CONFIG_PREFIX.length()));
          if (!(rawChoices instanceof List)) {
            throw new IOException("wrong type " + rawChoices);
          }
          List<?> listChoices = (List<?>) rawChoices;
          for (Object obj : listChoices) {
            if (!(obj instanceof Map)) {
              throw new IOException("wrong element type " + rawChoices);
            }
          }
          choices = (List<Map<String, ?>>) listChoices;
        } catch (IOException e) {
          logger.log(Level.WARNING, "Bad service config: " + txtRecord, e);
          continue;
        }
        serviceConfigs.addAll(choices);
      } else {
        logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{txtRecord});
      }
    }
    return serviceConfigs;
  }