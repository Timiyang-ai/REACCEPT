public static String substitute(Config config, String pathString) {

    // trim the leading and trailing spaces
    String trimmedPath = pathString.trim();

    if (isURL(trimmedPath)) {
      return substituteURL(config, trimmedPath);
    }

    // get platform independent file separator
    String fileSeparator = Matcher.quoteReplacement(File.separator);

    // split the trimmed path into a list of components
    List<String> fixedList = Arrays.asList(trimmedPath.split(fileSeparator));
    List<String> list = new LinkedList<>(fixedList);

    // substitute various variables
    for (int i = 0; i < list.size(); i++) {
      String elem = list.get(i);

      if ("${HOME}".equals(elem) || "~".equals(elem)) {
        list.set(i, System.getProperty("user.home"));

      } else if ("${JAVA_HOME}".equals(elem)) {
        String javaPath = System.getenv("JAVA_HOME");
        if (javaPath != null) {
          list.set(i, javaPath);
        }
      } else if (isToken(elem)) {
        Matcher m = TOKEN_PATTERN.matcher(elem);
        if (m.matches()) {
          String token = m.group(1);
          try {
            // For backwards compatibility the ${TOPOLOGY} token will match Key.TOPOLOGY_NAME
            if ("TOPOLOGY".equals(token)) {
              token = "TOPOLOGY_NAME";
            }
            Key key = Key.valueOf(token);
            String value = config.getStringValue(key);
            if (value == null) {
              throw new IllegalArgumentException(String.format("Config value %s contains "
                      + "substitution token %s but the corresponding config setting %s not found",
                  pathString, elem, key.value()));
            }
            list.set(i, value);
          } catch (IllegalArgumentException e) {
            LOG.warning(String.format("Config value %s contains substitution token %s which is "
                    + "not defined in the Key enum, which is required for token substitution",
                pathString, elem));
          }
        }
      }
    }

    return combinePaths(list);
  }