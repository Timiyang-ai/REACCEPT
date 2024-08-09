@Test
	public void testNotifyCreditAvailable() throws Exception {
		final CreditBasedPartitionRequestClientHandler handler = new CreditBasedPartitionRequestClientHandler();
		final EmbeddedChannel channel = new EmbeddedChannel(handler);
		final PartitionRequestClient client = new PartitionRequestClient(
			channel, handler, mock(ConnectionID.class), mock(PartitionRequestClientFactory.class));

		final NetworkBufferPool networkBufferPool = new NetworkBufferPool(10, 32);
		final SingleInputGate inputGate = createSingleInputGate();
		final RemoteInputChannel inputChannel1 = createRemoteInputChannel(inputGate, client);
		final RemoteInputChannel inputChannel2 = createRemoteInputChannel(inputGate, client);
		try {
			final BufferPool bufferPool = networkBufferPool.createBufferPool(6, 6);
			inputGate.setBufferPool(bufferPool);
			final int numExclusiveBuffers = 2;
			inputGate.assignExclusiveSegments(networkBufferPool, numExclusiveBuffers);

			inputChannel1.requestSubpartition(0);
			inputChannel2.requestSubpartition(0);

			// The two input channels should send partition requests
			assertTrue(channel.isWritable());
			Object readFromOutbound = channel.readOutbound();
			assertThat(readFromOutbound, instanceOf(PartitionRequest.class));
			assertEquals(inputChannel1.getInputChannelId(), ((PartitionRequest) readFromOutbound).receiverId);
			assertEquals(2, ((PartitionRequest) readFromOutbound).credit);

			readFromOutbound = channel.readOutbound();
			assertThat(readFromOutbound, instanceOf(PartitionRequest.class));
			assertEquals(inputChannel2.getInputChannelId(), ((PartitionRequest) readFromOutbound).receiverId);
			assertEquals(2, ((PartitionRequest) readFromOutbound).credit);

			// The buffer response will take one available buffer from input channel, and it will trigger
			// requesting (backlog + numExclusiveBuffers - numAvailableBuffers) floating buffers
			final BufferResponse bufferResponse1 = createBufferResponse(
				TestBufferFactory.createBuffer(32), 0, inputChannel1.getInputChannelId(), 1);
			final BufferResponse bufferResponse2 = createBufferResponse(
				TestBufferFactory.createBuffer(32), 0, inputChannel2.getInputChannelId(), 1);
			handler.channelRead(mock(ChannelHandlerContext.class), bufferResponse1);
			handler.channelRead(mock(ChannelHandlerContext.class), bufferResponse2);

			assertEquals(2, inputChannel1.getUnannouncedCredit());
			assertEquals(2, inputChannel2.getUnannouncedCredit());

			channel.runPendingTasks();

			// The two input channels should notify credits availability via the writable channel
			readFromOutbound = channel.readOutbound();
			assertThat(readFromOutbound, instanceOf(AddCredit.class));
			assertEquals(inputChannel1.getInputChannelId(), ((AddCredit) readFromOutbound).receiverId);
			assertEquals(2, ((AddCredit) readFromOutbound).credit);

			readFromOutbound = channel.readOutbound();
			assertThat(readFromOutbound, instanceOf(AddCredit.class));
			assertEquals(inputChannel2.getInputChannelId(), ((AddCredit) readFromOutbound).receiverId);
			assertEquals(2, ((AddCredit) readFromOutbound).credit);
			assertNull(channel.readOutbound());

			ByteBuf channelBlockingBuffer = blockChannel(channel);

			// Trigger notify credits availability via buffer response on the condition of an un-writable channel
			final BufferResponse bufferResponse3 = createBufferResponse(
				TestBufferFactory.createBuffer(32), 1, inputChannel1.getInputChannelId(), 1);
			handler.channelRead(mock(ChannelHandlerContext.class), bufferResponse3);

			assertEquals(1, inputChannel1.getUnannouncedCredit());
			assertEquals(0, inputChannel2.getUnannouncedCredit());

			channel.runPendingTasks();

			// The input channel will not notify credits via un-writable channel
			assertFalse(channel.isWritable());
			assertNull(channel.readOutbound());

			// Flush the buffer to make the channel writable again
			channel.flush();
			assertSame(channelBlockingBuffer, channel.readOutbound());

			// The input channel should notify credits via channel's writability changed event
			assertTrue(channel.isWritable());
			readFromOutbound = channel.readOutbound();
			assertThat(readFromOutbound, instanceOf(AddCredit.class));
			assertEquals(1, ((AddCredit) readFromOutbound).credit);
			assertEquals(0, inputChannel1.getUnannouncedCredit());
			assertEquals(0, inputChannel2.getUnannouncedCredit());

			// no more messages
			assertNull(channel.readOutbound());
		} finally {
			// Release all the buffer resources
			inputGate.close();

			networkBufferPool.destroyAllBufferPools();
			networkBufferPool.destroy();
		}
	}