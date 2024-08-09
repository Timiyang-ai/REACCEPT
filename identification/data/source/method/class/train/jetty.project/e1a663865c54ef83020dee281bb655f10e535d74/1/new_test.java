@SuppressWarnings("unchecked")
    @Test
    public void testSyn()
    {
        Stream stream = new StandardStream(synStreamFrame.getStreamId(), synStreamFrame.getPriority(), session, null, null);
        Set<Stream> streams = new HashSet<>();
        streams.add(stream);
        when(synStreamFrame.isClose()).thenReturn(false);
        PushInfo pushInfo = new PushInfo(new Fields(), false);
        when(session.getStreams()).thenReturn(streams);
        stream.push(pushInfo, new Promise.Adapter<Stream>());
        verify(session).syn(argThat(new PushSynInfoMatcher(stream.getId(), pushInfo)),
                any(StreamFrameListener.class), any(Promise.class));
    }