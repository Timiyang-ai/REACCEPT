public static synchronized void startup(StringId msgID, Object[] params) {
    String message = msgID.toLocalizedString(params);

    if (listener != null) {
      listener.setStatus(message);
    }

    logger.info(message);
  }