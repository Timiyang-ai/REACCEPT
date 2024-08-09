public String getFormattedFileList() {
    return files.stream().map(File::getName).collect(Collectors.joining(", "));
  }