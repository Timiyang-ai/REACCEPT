  private void set(String key, String value0, String value1) throws Exception {
    DiskLruCache.Editor editor = cache.edit(key);
    setString(editor, 0, value0);
    setString(editor, 1, value1);
    editor.commit();
  }