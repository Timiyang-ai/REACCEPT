public synchronized void unregisterOutputStream(String commandId) {

    flush();
    OutputStreamWriter oldWriter = commandIdToConsoleWriter.remove(commandId);

    // We better have removed something, or commandId was invalid.
    Preconditions.checkState(oldWriter != null);
  }