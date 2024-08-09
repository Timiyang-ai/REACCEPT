  @Test
  public void newStream_racesWithReprocessIdleMode() throws Exception {
    SubchannelPicker picker = new SubchannelPicker() {
      @Override public PickResult pickSubchannel(PickSubchannelArgs args) {
        // Assume entering idle mode raced with the pick
        delayedTransport.reprocess(null);
        // Act like IDLE LB
        return PickResult.withNoResult();
      }
    };

    // Because there is no pending stream yet, it will do nothing but save the picker.
    delayedTransport.reprocess(picker);

    ClientStream stream = delayedTransport.newStream(method, headers, callOptions);
    stream.start(streamListener);
    assertTrue(delayedTransport.hasPendingStreams());
    verify(transportListener).transportInUse(true);
  }