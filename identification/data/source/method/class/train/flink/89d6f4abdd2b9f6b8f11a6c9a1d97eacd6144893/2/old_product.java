@Override
	public void writeRecord(T record) throws IOException, InterruptedException {

		// Get a write buffer from the broker
		if (this.uncompressedDataBuffer == null) {

			requestWriteBuffersFromBroker();
		}

		if (this.closeRequested) {
			throw new IOException("Channel is aready requested to be closed");
		}

		// Check if we can accept new records or if there are still old
		// records to be transmitted
		if (this.compressor != null) {
			while (this.serializationBuffer.dataLeftFromPreviousSerialization()) {

				this.serializationBuffer.read(this.uncompressedDataBuffer);
				if (this.uncompressedDataBuffer.remaining() == 0) {

					this.compressor.compress();
					// this.leasedWriteBuffer.flip();
					releaseWriteBuffers();
					requestWriteBuffersFromBroker();
				}
			}
		} else {
			while (this.serializationBuffer.dataLeftFromPreviousSerialization()) {

				this.serializationBuffer.read(this.uncompressedDataBuffer);
				if (this.uncompressedDataBuffer.remaining() == 0) {
					releaseWriteBuffers();
					requestWriteBuffersFromBroker();
				}
			}
		}

		if (this.serializationBuffer.dataLeftFromPreviousSerialization()) {
			throw new IOException("Serialization buffer is expected to be empty!");
		}

		this.serializationBuffer.serialize(record);

		if (this.compressor != null) {
			this.serializationBuffer.read(this.uncompressedDataBuffer);

			if (this.uncompressedDataBuffer.remaining() == 0) {
				this.compressor.compress();
				// this.leasedWriteBuffer.flip();
				releaseWriteBuffers();
			}
		} else {
			this.serializationBuffer.read(this.uncompressedDataBuffer);
			if (this.uncompressedDataBuffer.remaining() == 0) {
				releaseWriteBuffers();
			}
		}
	}