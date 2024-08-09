private void createDB(final String input) throws BaseXException {
    new CreateDB(DBNAME, input == null ? DOC : input).execute(CONTEXT);
  }