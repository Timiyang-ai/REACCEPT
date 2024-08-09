public File resolve(final String path) {
    if (path != null && !path.isEmpty()) {
      File loc = new File(path);
      if (!loc.isAbsolute()) {
        loc = new File(site_path, path);
      }
      try {
        return loc.getCanonicalFile();
      } catch (IOException e) {
        return loc.getAbsoluteFile();
      }
    }
    return null;
  }