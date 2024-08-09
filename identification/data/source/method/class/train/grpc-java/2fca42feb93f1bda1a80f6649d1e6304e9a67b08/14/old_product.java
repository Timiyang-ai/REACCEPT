public RootChannelList getRootChannels(long fromId, int maxPageSize) {
    List<Instrumented<ChannelStats>> channelList = new ArrayList<Instrumented<ChannelStats>>();
    Iterator<Instrumented<ChannelStats>> iterator
        = rootChannels.tailMap(fromId).values().iterator();

    while (iterator.hasNext() && channelList.size() < maxPageSize) {
      channelList.add(iterator.next());
    }
    return new RootChannelList(channelList, !iterator.hasNext());
  }