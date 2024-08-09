public static byte[] delete(final Data data, final TokenList res) {
    for(final byte[] key : res) {
      final IOFile file = data.meta.binary(string(key));
      if(file == null || !file.delete()) return key;
    }
    return null;
  }