public static MigrationVersion parse(String raw) {

    if (raw.startsWith("V") || raw.startsWith("v")) {
      raw = raw.substring(1);
    }

    String comment = "";
    String value = raw;
    int commentStart = raw.indexOf("__");
    if (commentStart > -1) {
      // trim off the trailing comment
      comment = raw.substring(commentStart + 2);
      value = value.substring(0, commentStart);
    }

    value = value.replace('_', '.');

    String[] sections = value.split("[\\.-]");

    if ("r".equalsIgnoreCase(sections[0])) {
      // a "repeatable" version (does not have a version number)
      return new MigrationVersion(raw, comment);
    }

    boolean[] underscores = new boolean[sections.length];
    int[] ordering = new int[sections.length];

    int delimiterPos = 0;
    int stopIndex = 0;
    for (int i = 0; i < sections.length; i++) {
      try {
        ordering[i] = Integer.parseInt(sections[i]);
        stopIndex++;

        delimiterPos += sections[i].length();
        underscores[i] = (delimiterPos < raw.length() - 1 && raw.charAt(delimiterPos) == '_');
        delimiterPos++;
      } catch (NumberFormatException e) {
        // stop parsing
        break;
      }
    }

    int[] actualOrder = Arrays.copyOf(ordering, stopIndex);
    boolean[] actualUnderscores = Arrays.copyOf(underscores, stopIndex);

    return new MigrationVersion(raw, actualOrder, actualUnderscores, comment);
  }