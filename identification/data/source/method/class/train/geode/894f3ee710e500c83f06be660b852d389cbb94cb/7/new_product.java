public void startup(String message, Object... params) {
    notNull(message, "Invalid message '" + message + "' specified");
    notNull(params, "Invalid params specified");

    String formattedMessage = String.format(message, params);

    StartupStatusListener listener = StartupStatusListenerRegistry.getStartupListener();
    if (listener != null) {
      listener.setStatus(formattedMessage);
    }

    logger.accept(formattedMessage);
  }