@Test
	public void testNotifyCreditAvailable() throws Exception {
		final NetworkBufferPool networkBufferPool = new NetworkBufferPool(10, 32);
		final SingleInputGate inputGate = createSingleInputGate();
		final RemoteInputChannel inputChannel1 = createRemoteInputChannel(inputGate);
		final RemoteInputChannel inputChannel2 = createRemoteInputChannel(inputGate);
		inputGate.setInputChannel(inputChannel1.getPartitionId().getPartitionId(), inputChannel1);
		inputGate.setInputChannel(inputChannel2.getPartitionId().getPartitionId(), inputChannel2);
		try {
			final BufferPool bufferPool = networkBufferPool.createBufferPool(6, 6);
			inputGate.setBufferPool(bufferPool);
			final int numExclusiveBuffers = 2;
			inputGate.assignExclusiveSegments(networkBufferPool, numExclusiveBuffers);

			final CreditBasedClientHandler handler = new CreditBasedClientHandler();
			final EmbeddedChannel channel = new EmbeddedChannel(handler);

			// The PartitionRequestClient is tied to PartitionRequestClientHandler currently, so we
			// have to add input channels in CreditBasedClientHandler explicitly
			inputChannel1.requestSubpartition(0);
			inputChannel2.requestSubpartition(0);
			handler.addInputChannel(inputChannel1);
			handler.addInputChannel(inputChannel2);

			// The buffer response will take one available buffer from input channel, and it will trigger
			// requesting (backlog + numExclusiveBuffers -  numAvailableBuffers) floating buffers
			final BufferResponse bufferResponse1 = createBufferResponse(
				TestBufferFactory.createBuffer(32), 0, inputChannel1.getInputChannelId(), 1);
			final BufferResponse bufferResponse2 = createBufferResponse(
				TestBufferFactory.createBuffer(32), 0, inputChannel2.getInputChannelId(), 1);
			handler.channelRead(mock(ChannelHandlerContext.class), bufferResponse1);
			handler.channelRead(mock(ChannelHandlerContext.class), bufferResponse2);

			// The PartitionRequestClient is tied to PartitionRequestClientHandler currently, so we
			// have to notify credit available in CreditBasedClientHandler explicitly
			handler.notifyCreditAvailable(inputChannel1);
			handler.notifyCreditAvailable(inputChannel2);

			assertEquals(2, inputChannel1.getUnannouncedCredit());
			assertEquals(2, inputChannel2.getUnannouncedCredit());

			channel.runPendingTasks();

			// The two input channels should notify credits via writable channel
			assertTrue(channel.isWritable());
			Object readFromOutbound = channel.readOutbound();
			assertThat(readFromOutbound, instanceOf(AddCredit.class));
			assertEquals(2, ((AddCredit) readFromOutbound).credit);
			readFromOutbound = channel.readOutbound();
			assertThat(readFromOutbound, instanceOf(AddCredit.class));
			assertEquals(2, ((AddCredit) readFromOutbound).credit);
			assertNull(channel.readOutbound());

			final int highWaterMark = channel.config().getWriteBufferHighWaterMark();
			// Set the writer index to the high water mark to ensure that all bytes are written
			// to the wire although the buffer is "empty".
			ByteBuf channelBlockingBuffer = Unpooled.buffer(highWaterMark).writerIndex(highWaterMark);
			channel.write(channelBlockingBuffer);

			// Trigger notify credits available via buffer response on the condition of un-writable channel
			final BufferResponse bufferResponse3 = createBufferResponse(
				TestBufferFactory.createBuffer(32), 1, inputChannel1.getInputChannelId(), 1);
			handler.channelRead(mock(ChannelHandlerContext.class), bufferResponse3);
			handler.notifyCreditAvailable(inputChannel1);

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
			inputGate.releaseAllResources();

			networkBufferPool.destroyAllBufferPools();
			networkBufferPool.destroy();
		}
	}