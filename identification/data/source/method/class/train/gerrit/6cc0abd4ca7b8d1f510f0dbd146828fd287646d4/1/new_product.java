public Path resolve(String path) {
    if (path != null && !path.isEmpty()) {
      Path loc = site_path.resolve(path).normalize();
      try {
        return loc.toRealPath();
      } catch (IOException e) {
        return loc.toAbsolutePath();
      }
    }
    return null;
  }