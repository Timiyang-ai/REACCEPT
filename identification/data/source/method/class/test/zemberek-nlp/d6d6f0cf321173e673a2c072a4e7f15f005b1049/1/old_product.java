public List<String> getLemmas() {
    List<String> lemmas = Lists.newArrayListWithCapacity(2);
    lemmas.add(item.root);

    String previousStem = getGroup(0).surfaceForm();
    if (!previousStem.equals(item.root)) {
      if (previousStem.endsWith("ğ")) {
        previousStem = previousStem.substring(0, previousStem.length() - 1) + "k";
      }
    }

    if (groupBoundaries.length > 1) {
      for (int i = 1; i < groupBoundaries.length; i++) {
        MorphemeGroup ig = getGroup(i);
        MorphemeSurface suffixData = ig.morphemes.get(0);

        String surface = suffixData.surface;
        if (suffixData.surface.endsWith("ğ")) {
          surface = surface.substring(0, surface.length() - 1) + "k";
        }
        String stem = previousStem + surface;
        if (!lemmas.contains(stem)) {
          lemmas.add(stem);
        }
        previousStem = previousStem + ig.surfaceForm();
      }
    }
    return lemmas;
  }