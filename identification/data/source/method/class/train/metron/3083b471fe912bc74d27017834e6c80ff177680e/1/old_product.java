@Override
  public void distribute(JSONObject message, MessageRoute route, Context context) throws ExecutionException {
    getBuilder(route, context).apply(message);
  }