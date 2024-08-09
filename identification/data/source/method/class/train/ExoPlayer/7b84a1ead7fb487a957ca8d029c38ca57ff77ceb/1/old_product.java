public List<Download> getCurrentDownloads() {
    return Collections.unmodifiableList(new ArrayList<>(downloads));
  }