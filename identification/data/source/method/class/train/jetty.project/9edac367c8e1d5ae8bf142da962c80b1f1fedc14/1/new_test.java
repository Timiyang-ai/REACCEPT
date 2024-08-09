@SuppressWarnings("unchecked")
    @Test
    public void testSyn()
    {
        Stream stream = new StandardStream(synStreamFrame.getStreamId(), synStreamFrame.getPriority(), session, null,null);
        Set<Stream> streams = new HashSet<>();
        streams.add(stream);
        when(synStreamFrame.isClose()).thenReturn(false);
        SynInfo synInfo = new SynInfo(false);
        when(session.getStreams()).thenReturn(streams);
        stream.syn(synInfo);
        verify(session).syn(argThat(new PushSynInfoMatcher(stream.getId(), synInfo)), any(StreamFrameListener.class), anyLong(), any(TimeUnit.class), any(Promise.class));
    }