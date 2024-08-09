public RootChannelList getRootChannels(long fromId, int maxPageSize) {
    List<InternalInstrumented<ChannelStats>> channelList
        = new ArrayList<InternalInstrumented<ChannelStats>>();
    Iterator<InternalInstrumented<ChannelStats>> iterator
        = rootChannels.tailMap(fromId).values().iterator();

    while (iterator.hasNext() && channelList.size() < maxPageSize) {
      channelList.add(iterator.next());
    }
    return new RootChannelList(channelList, !iterator.hasNext());
  }