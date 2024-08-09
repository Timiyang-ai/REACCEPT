public Publisher<Payload> requestChannel(final Publisher<Payload> payloadStream) {
		return startStream(nextStreamId(), FrameType.REQUEST_CHANNEL, payloadStream);
	}