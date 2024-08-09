  protected boolean isSymbolicLink(File file) throws IOException {
    return NativePosixFiles.lstat(file.getPath()).isSymbolicLink();
  }