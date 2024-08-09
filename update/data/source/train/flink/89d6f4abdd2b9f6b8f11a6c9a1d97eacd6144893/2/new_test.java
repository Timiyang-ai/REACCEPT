@Test
	@PrepareForTest(CompressionLoader.class)
	public void writeRecordTest() throws IOException, InterruptedException {

		final StringRecord record = new StringRecord("abc");
		final Decompressor decompressorMock = mock(Decompressor.class);
		this.uncompressedDataBuffer = mock(Buffer.class);
		// BufferPairResponse bufferPair = mock(BufferPairResponse.class);
		// when(bufferPair.getUncompressedDataBuffer()).thenReturn(this.uncompressedDataBuffer,
		// this.uncompressedDataBuffer, this.uncompressedDataBuffer,null);
		// when(bufferPair.getCompressedDataBuffer()).thenReturn(this.uncompressedDataBuffer,
		// this.uncompressedDataBuffer, this.uncompressedDataBuffer,null);
		PowerMockito.mockStatic(CompressionLoader.class);
		when(
			CompressionLoader.getDecompressorByCompressionLevel(Matchers.any(CompressionLevel.class),
				Matchers.any(FileInputChannel.class))).thenReturn(
			decompressorMock);

		@SuppressWarnings("unchecked")
		final OutputGate<StringRecord> outGate = mock(OutputGate.class);
		final ByteBufferedOutputChannelBroker outputBroker = mock(ByteBufferedOutputChannelBroker.class);
		when(outputBroker.requestEmptyWriteBuffer()).thenReturn(this.uncompressedDataBuffer);

		when(outputBroker.hasDataLeftToTransmit()).thenReturn(true);

		when(this.serializationBuffer.dataLeftFromPreviousSerialization()).thenReturn(false, false, true, true, false,
			false);
		// try {
		// when(this.serializationBuffer.readData(Matchers.any(ReadableByteChannel.class))).thenReturn(null, record);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		when(this.uncompressedDataBuffer.remaining()).thenReturn(0);

		// setup test-object
		FileOutputChannel<StringRecord> fileOutputChannel = new FileOutputChannel<StringRecord>(outGate, 1,
			new ChannelID(), new ChannelID(), CompressionLevel.NO_COMPRESSION);
		fileOutputChannel.setByteBufferedOutputChannelBroker(outputBroker);

		Whitebox.setInternalState(fileOutputChannel, "serializationBuffer", this.serializationBuffer);

		// correct run
		try {
			fileOutputChannel.writeRecord(record);
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}

		// Close Channel to test EOFException
		fileOutputChannel.requestClose();
		// No acknowledgment from consumer yet so the channel should still be open
		assertEquals(false, fileOutputChannel.isClosed());
		when(outputBroker.hasDataLeftToTransmit()).thenReturn(false);
		// Received acknowledgment the channel should be closed now
		assertEquals(true, fileOutputChannel.isClosed());
		try {
			fileOutputChannel.writeRecord(record);
			fail();
		} catch (IOException e) {
			// expected a IOException
		}
	}