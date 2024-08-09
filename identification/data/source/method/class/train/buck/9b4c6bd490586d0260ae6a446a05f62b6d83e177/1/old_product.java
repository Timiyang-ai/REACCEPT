public synchronized void registerOutputStream(String commandId, OutputStream outputStream) {
    Preconditions.checkNotNull(commandId);
    Preconditions.checkNotNull(outputStream);

    flush();
    commandIdToConsoleWriter.put(commandId, utf8OutputStreamWriter(outputStream));
  }