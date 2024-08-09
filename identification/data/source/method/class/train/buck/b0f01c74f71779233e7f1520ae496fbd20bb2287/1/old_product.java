public static ImmutableSet<String> combine(
      @Nullable Set<String> prefixes, String base, @Nullable Set<String> suffixes) {

    ImmutableSet<String> suffixedSet;
    if (suffixes == null || suffixes.isEmpty()) {
      suffixedSet = ImmutableSet.of(base);
    } else {
      ImmutableSet.Builder<String> suffixedBuilder = ImmutableSet.builder();
      for (String suffix : suffixes) {
        suffixedBuilder.add(base + suffix);
      }
      suffixedSet = suffixedBuilder.build();
    }

    if (prefixes == null || prefixes.isEmpty()) {
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