public static synchronized void startup(final String msg, final Object... params) {
    notNull(msg, "Invalid msgId '" + msg + "' specified");
    notNull(params, "Invalid params specified");

    String message = String.format(msg, params);

    if (listener != null) {
      listener.setStatus(message);
    }

    logger.info(message);
  }