public static MigrationVersion parse(String raw) {

    String value = raw.replace("__",".");
    value = value.replace('_','.');

    String[] sections = value.split("\\.");

    int[] ordering = new int[sections.length];

    int stopIndex = 0;
    for (int i = 0; i < sections.length; i++) {
      try {
        ordering[i] = Integer.parseInt(sections[i]);
        stopIndex++;
      } catch (NumberFormatException e) {
        // stop parsing
        break;
      }
    }

    int[] actualOrder = new int[stopIndex];
    System.arraycopy(ordering, 0, actualOrder, 0, stopIndex);

    return new MigrationVersion(raw, actualOrder);
  }