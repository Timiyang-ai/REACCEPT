@Test
  public void validatePath() throws InvalidPathException {
    // check valid paths
    PathUtils.validatePath("/foo/bar");
    PathUtils.validatePath("/foo/bar/");
    PathUtils.validatePath("/foo/./bar/");
    PathUtils.validatePath("/foo/././bar/");
    PathUtils.validatePath("/foo/../bar");
    PathUtils.validatePath("/foo/../bar/");

    // check invalid paths
    ArrayList<String> invalidPaths = new ArrayList<>();
    invalidPaths.add(null);
    invalidPaths.add("");
    invalidPaths.add(" /");
    invalidPaths.add("/ ");
    for (String invalidPath : invalidPaths) {
      try {
        PathUtils.validatePath(invalidPath);
        fail("validatePath(" + invalidPath + ") did not fail");
      } catch (InvalidPathException e) {
        // this is expected
      }
    }
  }