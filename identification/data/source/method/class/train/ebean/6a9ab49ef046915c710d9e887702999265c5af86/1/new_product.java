boolean isValid(Set<String> tables, long sinceNanoTime) {
    for (String tableName : tables) {
      Long modTime = tableModStamp.get(tableName);
      if (modTime != null && modTime >= sinceNanoTime) {
        if (log.isTraceEnabled()) {
          log.trace("Invalidate on table:{}", tableName);
        }
        return false;
      }
    }
    return true;
  }