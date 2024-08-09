public static String getAbbreviatedName(final String className, final int len) {
      if (len <= 0) {
        throw new IllegalArgumentException("len must be > 0");
      }
      if (className == null) {
        return StringUtils.EMPTY;
      }

      int availableSpace = len;
      int packageLevels = StringUtils.countMatches(className, '.');
      String[] output = new String[packageLevels + 1];
      int endIndex = className.length() - 1;
      for (int level = packageLevels; level >= 0; level--) {
        int startIndex = className.lastIndexOf('.', endIndex);
        String part = className.substring(startIndex + 1, endIndex + 1);
        availableSpace -= part.length();
        if (level > 0) {
          // all elements except top level require an additional char space
          availableSpace--;
        }
        if (level == packageLevels) {
          // ClassName is always complete
          output[level] = part;
        } else {
          if (availableSpace > 0) {
            output[level] = part;
          } else {
            // if no space is left still the first char is used
            output[level] = part.substring(0, 1);
          }
        }
        endIndex = startIndex - 1;
      }

      return StringUtils.join(output, '.');
    }