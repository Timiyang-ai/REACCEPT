@Nullable
  public InternalInstrumented<ChannelStats> getSubchannel(long id) {
    return subchannels.get(id);
  }