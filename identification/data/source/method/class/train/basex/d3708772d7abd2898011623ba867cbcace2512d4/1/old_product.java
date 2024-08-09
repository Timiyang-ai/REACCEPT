private static void createDB(final String input) throws BaseXException {
    new CreateDB(DB, input == null ? DOC : input).execute(CONTEXT);
  }