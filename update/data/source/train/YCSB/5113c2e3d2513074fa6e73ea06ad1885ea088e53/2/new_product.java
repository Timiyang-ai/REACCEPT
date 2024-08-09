@Override
  public Status update(String table, String key,
      HashMap<String, ByteIterator> values) {
    // Insert and updates provide the same functionality
    return insert(table, key, values);
  }