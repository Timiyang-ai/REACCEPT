boolean isValid(Set<String> tables, long sinceTimestamp) {
    for (String tableName : tables) {
      Long modTime = tableModStamp.get(tableName);
      if (modTime != null && modTime >= sinceTimestamp ) {
        if (log.isTraceEnabled()) {
          log.trace("Invalidate on table:{}", tableName);
        }
        return false;
      }
    }
    return true;
  }