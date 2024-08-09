public static boolean delete(final Data data, final String res) {
    final IOFile file = data.meta.binary(res);
    return file != null && file.delete();
  }