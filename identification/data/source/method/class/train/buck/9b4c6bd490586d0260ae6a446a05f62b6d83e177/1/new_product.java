public synchronized void registerOutputStream(String commandId, OutputStream outputStream) {

    flush();
    commandIdToConsoleWriter.put(commandId, utf8OutputStreamWriter(outputStream));
  }