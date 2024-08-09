  private void unregisterOutputStream(String commandId) {
    assertNotNull(
        String.format("Command id [%s] never had a registered writer.", commandId),
        commandIdToConsoleWriter.remove(commandId));
  }