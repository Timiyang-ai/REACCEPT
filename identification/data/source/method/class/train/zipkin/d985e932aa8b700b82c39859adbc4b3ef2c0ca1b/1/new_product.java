@Nullable
  public static JsonParser enterPath(JsonParser parser, String path1, String path2)
      throws IOException {
    return enterPath(parser, path1) != null ? enterPath(parser, path2) : null;
  }