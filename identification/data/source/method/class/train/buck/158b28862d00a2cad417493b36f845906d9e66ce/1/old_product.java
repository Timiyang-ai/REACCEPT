ImmutableList<String> getRustLibraryFlags() {
    ImmutableList.Builder<String> builder = ImmutableList.builder();

    builder.addAll(getRustCompilerFlags());
    builder.addAll(delegate.getListWithoutComments(SECTION, RUSTC_LIBRARY_FLAGS, ' '));

    return builder.build();
  }