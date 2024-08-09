  private void registerOutputStream(String commandId, FakeOutputStream outputStream) {
    commandIdToConsoleWriter.put(commandId, ConsoleHandler.utf8OutputStreamWriter(outputStream));
  }