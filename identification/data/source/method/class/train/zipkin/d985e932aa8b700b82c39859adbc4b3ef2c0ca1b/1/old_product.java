@Nullable
  public static JsonReader enterPath(JsonReader reader, String path1, String path2)
      throws IOException {
    return enterPath(reader, path1) != null ? enterPath(reader, path2) : null;
  }