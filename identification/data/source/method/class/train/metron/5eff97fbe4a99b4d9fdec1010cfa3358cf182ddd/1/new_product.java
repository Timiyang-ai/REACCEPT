@Override
  public void distribute(MessageRoute route, Context context) {
    try {
      ProfileBuilder builder = getBuilder(route, context);
      builder.apply(route.getMessage(), route.getTimestamp());

    } catch(ExecutionException e) {
      LOG.error("Unexpected error", e);
      throw new RuntimeException(e);
    }
  }