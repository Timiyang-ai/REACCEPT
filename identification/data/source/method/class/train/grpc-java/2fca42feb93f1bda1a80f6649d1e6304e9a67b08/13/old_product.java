@Nullable
  public Instrumented<ChannelStats> getChannel(long id) {
    return rootChannels.get(id);
  }