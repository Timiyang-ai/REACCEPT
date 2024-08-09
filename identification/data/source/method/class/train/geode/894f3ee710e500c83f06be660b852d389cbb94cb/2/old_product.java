public static synchronized void startup(final String msg, final Object... params) {
    notNull(msg, "Invalid msgId '" + msg + "' specified");
    notNull(params, "Invalid params '" + params + "' specified");

    String message = (params == null) ? msg : String.format(msg, params);

    if (listener != null) {
      listener.setStatus(message);
    }

    logger.info(message);
  }