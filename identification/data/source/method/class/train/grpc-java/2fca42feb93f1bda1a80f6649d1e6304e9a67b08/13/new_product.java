@Nullable
  public InternalInstrumented<ChannelStats> getChannel(long id) {
    return rootChannels.get(id);
  }