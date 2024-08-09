boolean isValid(Set<String> tables, long sinceTimestamp) {
    for (String tableName : tables) {
      Long modTime = tableModStamp.get(tableName);
      if (modTime != null && modTime > sinceTimestamp ) {
        return false;
      }
    }
    return true;
  }