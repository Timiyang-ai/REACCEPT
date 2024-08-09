@Nullable
  public Instrumented<ChannelStats> getSubchannel(long id) {
    return subchannels.get(id);
  }