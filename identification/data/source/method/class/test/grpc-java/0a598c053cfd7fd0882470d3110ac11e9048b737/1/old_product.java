@Nullable
  @SuppressWarnings("BetaApi") // Verify isn't all that beta
  @VisibleForTesting
  static Map<String, Object> maybeChooseServiceConfig(
      Map<String, Object> choice, Random random, String hostname) {
    for (Entry<String, ?> entry : choice.entrySet()) {
      Verify.verify(SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()), "Bad key: %s", entry);
    }

    List<String> clientLanguages = getClientLanguagesFromChoice(choice);
    if (clientLanguages != null && clientLanguages.size() != 0) {
      boolean javaPresent = false;
      for (String lang : clientLanguages) {
        if ("java".equalsIgnoreCase(lang)) {
          javaPresent = true;
          break;
        }
      }
      if (!javaPresent) {
        return null;
      }
    }
    Double percentage = getPercentageFromChoice(choice);
    if (percentage != null) {
      int pct = percentage.intValue();
      Verify.verify(pct >= 0 && pct <= 100, "Bad percentage: %s", percentage);
      if (random.nextInt(100) >= pct) {
        return null;
      }
    }
    List<String> clientHostnames = getHostnamesFromChoice(choice);
    if (clientHostnames != null && clientHostnames.size() != 0) {
      boolean hostnamePresent = false;
      for (String clientHostname : clientHostnames) {
        if (clientHostname.equals(hostname)) {
          hostnamePresent = true;
          break;
        }
      }
      if (!hostnamePresent) {
        return null;
      }
    }
    return ServiceConfigUtil.getObject(choice, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY);
  }