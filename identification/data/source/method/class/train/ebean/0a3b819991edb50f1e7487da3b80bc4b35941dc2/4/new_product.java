public String convertQuotedIdentifiers(String dbName) {
    // Ignore null values e.g. schema name or catalog
    if (dbName != null && !dbName.isEmpty()) {
      if (dbName.charAt(0) == BACK_TICK) {
        if (dbName.charAt(dbName.length() - 1) == BACK_TICK) {
          return openQuote + dbName.substring(1, dbName.length() - 1) + closeQuote;
        } else {
          logger.error("Missing backquote on [" + dbName + "]");
        }
      } else if (allQuotedIdentifiers) {
        return openQuote + dbName + closeQuote;
      }
    }
    return dbName;
  }