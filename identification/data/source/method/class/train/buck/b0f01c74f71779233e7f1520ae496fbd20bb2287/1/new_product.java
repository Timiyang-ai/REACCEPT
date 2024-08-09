public static ImmutableSet<String> combine(
      Set<String> prefixes, String base, Set<String> suffixes) {

    ImmutableSet<String> suffixedSet;
    if (suffixes.isEmpty()) {
      suffixedSet = ImmutableSet.of(base);
    } else {
      ImmutableSet.Builder<String> suffixedBuilder = ImmutableSet.builder();
      for (String suffix : suffixes) {
        suffixedBuilder.add(base + suffix);
      }
      suffixedSet = suffixedBuilder.build();
    }

    if (prefixes.isEmpty()) {
      return suffixedSet;
    } else {
      ImmutableSet.Builder<String> builder = ImmutableSet.builder();
      for (String prefix : prefixes) {
        for (String suffix : suffixedSet) {
          builder.add(prefix + suffix);
        }
      }
      return builder.build();
    }
  }