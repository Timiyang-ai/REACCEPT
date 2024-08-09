public static synchronized void startup(final StringId msgId, final Object... params) {
    notNull(msgId, "Invalid msgId '" + msgId + "' specified");
    notNull(params, "Invalid params '" + params + "' specified");

    String message = msgId.toLocalizedString(params);

    if (listener != null) {
      listener.setStatus(message);
    }

    logger.info(message);
  }