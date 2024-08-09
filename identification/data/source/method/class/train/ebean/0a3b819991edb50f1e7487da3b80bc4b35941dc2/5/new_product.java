public String convertQuotedIdentifiers(String dbName) {
    // Ignore null values e.g. schema name or catalog
    if (dbName != null && !dbName.isEmpty()) {
      if (dbName.charAt(0) == BACK_TICK) {
        if (dbName.charAt(dbName.length() - 1) == BACK_TICK) {

          String quotedName = getOpenQuote();
          quotedName += dbName.substring(1, dbName.length() - 1);
          quotedName += getCloseQuote();

          return quotedName;

        } else {
          logger.error("Missing backquote on [" + dbName + "]");
        }
      }
    }
    return dbName;
  }