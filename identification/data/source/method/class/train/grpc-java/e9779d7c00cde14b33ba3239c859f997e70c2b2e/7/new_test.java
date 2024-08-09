  @Test
  public void reprocess_NoPendingStream() {
    SubchannelPicker picker = mock(SubchannelPicker.class);
    AbstractSubchannel subchannel = mock(AbstractSubchannel.class);
    when(subchannel.getInternalSubchannel()).thenReturn(mockInternalSubchannel);
    when(picker.pickSubchannel(any(PickSubchannelArgs.class))).thenReturn(
        PickResult.withSubchannel(subchannel));
    when(mockRealTransport.newStream(any(MethodDescriptor.class), any(Metadata.class),
            any(CallOptions.class))).thenReturn(mockRealStream);
    delayedTransport.reprocess(picker);
    verifyNoMoreInteractions(picker);
    verifyNoMoreInteractions(transportListener);

    // Though picker was not originally used, it will be saved and serve future streams.
    ClientStream stream = delayedTransport.newStream(method, headers, CallOptions.DEFAULT);
    verify(picker).pickSubchannel(new PickSubchannelArgsImpl(method, headers, CallOptions.DEFAULT));
    verify(mockInternalSubchannel).obtainActiveTransport();
    assertSame(mockRealStream, stream);
  }