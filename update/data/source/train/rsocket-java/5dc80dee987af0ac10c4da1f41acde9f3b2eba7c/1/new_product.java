public Publisher<Payload> requestChannel(final Publisher<Payload> payloadStream) {
		return startChannel(nextStreamId(), FrameType.REQUEST_CHANNEL, payloadStream);
	}