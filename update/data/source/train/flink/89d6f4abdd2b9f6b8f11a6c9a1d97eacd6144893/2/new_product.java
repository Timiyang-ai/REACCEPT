@Override
	public void writeRecord(T record) throws IOException, InterruptedException {

		// Get a write buffer from the broker
		if (this.dataBuffer == null) {
			requestWriteBufferFromBroker();
		}

		if (this.closeRequested) {
			throw new IOException("Channel is aready requested to be closed");
		}

		// Check if we can accept new records or if there are still old
		// records to be transmitted
		if (this.compressor != null) {
			while (this.serializationBuffer.dataLeftFromPreviousSerialization()) {

				this.serializationBuffer.read(this.dataBuffer);
				if (this.dataBuffer.remaining() == 0) {

					this.dataBuffer = this.compressor.compress(this.dataBuffer);
					// this.leasedWriteBuffer.flip();
					releaseWriteBuffer();
					requestWriteBufferFromBroker();
				}
			}
		} else {
			while (this.serializationBuffer.dataLeftFromPreviousSerialization()) {

				this.serializationBuffer.read(this.dataBuffer);
				if (this.dataBuffer.remaining() == 0) {
					releaseWriteBuffer();
					requestWriteBufferFromBroker();
				}
			}
		}

		if (this.serializationBuffer.dataLeftFromPreviousSerialization()) {
			throw new IOException("Serialization buffer is expected to be empty!");
		}

		this.serializationBuffer.serialize(record);

		if (this.compressor != null) {
			this.serializationBuffer.read(this.dataBuffer);

			if (this.dataBuffer.remaining() == 0) {
				this.dataBuffer = this.compressor.compress(this.dataBuffer);
				// this.leasedWriteBuffer.flip();
				releaseWriteBuffer();
			}
		} else {
			this.serializationBuffer.read(this.dataBuffer);
			if (this.dataBuffer.remaining() == 0) {
				releaseWriteBuffer();
			}
		}
	}