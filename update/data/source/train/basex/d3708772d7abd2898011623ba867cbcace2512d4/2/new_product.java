private static void createDB(final String input) throws BaseXException {
    new CreateDB(NAME, input == null ? DOC : input).execute(context);
  }