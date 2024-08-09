@Subscribe
  @Transactional
  public void onHostEvent(HostsRemovedEvent event) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(event.toString());
    }
  }