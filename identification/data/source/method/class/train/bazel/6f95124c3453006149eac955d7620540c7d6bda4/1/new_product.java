@VisibleForTesting
  static ImmutableList<String> processJavacopts(TurbineOptions turbineOptions) {
    ImmutableList<String> javacopts =
        JavacOptions.removeBazelSpecificFlags(
            JavacOptions.normalizeOptionsWithNormalizers(
                turbineOptions.javacOpts(), new JavacOptions.ReleaseOptionNormalizer()));

    ImmutableList.Builder<String> builder = ImmutableList.builder();
    builder.addAll(javacopts);

    // Disable compilation of implicit source files.
    // This is insurance: the sourcepath is empty, so we don't expect implicit sources.
    builder.add("-implicit:none");

    // Disable debug info
    builder.add("-g:none");

    // Enable MethodParameters
    builder.add("-parameters");

    // Compile-time jars always use Java 8
    if (javacopts.contains("--release")) {
      // javac doesn't allow mixing -source and --release, so use --release if it's already present
      // in javacopts.
      builder.add("--release");
      builder.add("8");
    } else {
      builder.add("-source");
      builder.add("8");
      builder.add("-target");
      builder.add("8");
    }

    if (!turbineOptions.processors().isEmpty()) {
      builder.add("-processor");
      builder.add(Joiner.on(',').join(turbineOptions.processors()));
    }

    return builder.build();
  }