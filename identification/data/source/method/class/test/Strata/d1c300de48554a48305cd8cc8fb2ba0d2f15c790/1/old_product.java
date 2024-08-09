public static ResourceLocator ofFile(File file) {
    ArgChecker.notNull(file, "file");
    String filename = file.toString();
    // convert Windows separators to unix
    filename = (File.separatorChar == '\\' ? filename.replace('\\', '/') : filename);
    return new ResourceLocator(FILE_URL_PREFIX + filename, Files.asByteSource(file));
  }