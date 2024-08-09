@Override
  public void distribute(JSONObject message, long timestamp, MessageRoute route, Context context) {
    try {
      ProfileBuilder builder = getBuilder(route, context);
      builder.apply(message, timestamp);

    } catch(ExecutionException e) {
      LOG.error("Unexpected error", e);
      throw new RuntimeException(e);
    }
  }