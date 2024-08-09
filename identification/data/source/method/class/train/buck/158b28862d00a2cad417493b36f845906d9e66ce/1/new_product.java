public ImmutableList<String> getRustcLibraryFlags(String section) {
    return ImmutableList.<String>builder()
        .addAll(getRustCompilerFlags(section))
        .addAll(getFlags(section, RUSTC_LIBRARY_FLAGS))
        .build();
  }