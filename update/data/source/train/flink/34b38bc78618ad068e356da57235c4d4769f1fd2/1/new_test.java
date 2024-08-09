@Test
	@PrepareForTest(CompressionLoader.class)
	public void deserializeNextRecordTest() throws IOException, InterruptedException {
		StringRecord record = new StringRecord("abc");
		Decompressor decompressorMock = mock(Decompressor.class);
		this.uncompressedDataBuffer = mock(Buffer.class);
		BufferPairResponse bufferPair = new BufferPairResponse(this.uncompressedDataBuffer, this.uncompressedDataBuffer);
		// BufferPairResponse bufferPair = mock(BufferPairResponse.class);
		// when(bufferPair.getUncompressedDataBuffer()).thenReturn(this.uncompressedDataBuffer,
		// this.uncompressedDataBuffer, null);

		PowerMockito.mockStatic(CompressionLoader.class);
		when(
			CompressionLoader.getDecompressorByCompressionLevel(Matchers.any(CompressionLevel.class),
				Matchers.any(FileInputChannel.class))).thenReturn(
			decompressorMock);

		@SuppressWarnings("unchecked")
		final InputGate<StringRecord> inGate = mock(InputGate.class);
		final ByteBufferedInputChannelBroker inputBroker = mock(ByteBufferedInputChannelBroker.class);
		when(inputBroker.getReadBufferToConsume()).thenReturn(bufferPair);
		try {
			when(
				this.deserializationBuffer.readData(Matchers.any(StringRecord.class),
					Matchers.any(ReadableByteChannel.class))).thenReturn(null, record);
		} catch (IOException e) {

		}
		when(this.uncompressedDataBuffer.remaining()).thenReturn(0);

		// setup test-object
		final FileInputChannel<StringRecord> fileInputChannel = new FileInputChannel<StringRecord>(inGate, 1,
			this.deserializer, new ChannelID(), new ChannelID(), CompressionLevel.NO_COMPRESSION);
		fileInputChannel.setInputChannelBroker(inputBroker);

		Whitebox.setInternalState(fileInputChannel, "deserializationBuffer", this.deserializationBuffer);

		// correct run
		try {
			fileInputChannel.readRecord(null);
		} catch (IOException e) {
			fail(StringUtils.stringifyException(e));
		}

		// Close Channel to test EOFException
		try {
			fileInputChannel.close();
		} catch (IOException e) {
			fail(StringUtils.stringifyException(e));
		} catch (InterruptedException e) {
			fail(StringUtils.stringifyException(e));
		}
		// No acknowledgment from consumer yet so the channel should still be open
		assertEquals(false, fileInputChannel.isClosed());
		fileInputChannel.processEvent(new ByteBufferedChannelCloseEvent());
		// Received acknowledgment the channel should be closed now
		assertEquals(true, fileInputChannel.isClosed());
		try {
			fileInputChannel.readRecord(null);
			fail();
		} catch (EOFException e) {
			// expected a EOFException
		} catch (IOException e) {
			// all other Exceptions are real failures
			e.printStackTrace();
			fail();
		}
	}