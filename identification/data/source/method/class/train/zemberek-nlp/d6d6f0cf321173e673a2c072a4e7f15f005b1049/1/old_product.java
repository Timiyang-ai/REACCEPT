public List<String> getStems() {
    List<String> stems = Lists.newArrayListWithCapacity(2);
    stems.add(getStem());
    String previousStem = getGroup(0).surfaceForm();
    if (groupBoundaries.length > 1) {
      for (int i = 1; i < groupBoundaries.length; i++) {
        MorphemeGroup ig = getGroup(i);
        MorphemeSurface suffixData = ig.morphemes.get(0);

        String surface = suffixData.surface;
        String stem = previousStem + surface;
        if (!stems.contains(stem)) {
          stems.add(stem);
        }
        previousStem = previousStem + ig.surfaceForm();
      }
    }
    return stems;
  }