private T deserializeNextRecord(final T target) throws IOException {

		if (this.dataBuffer == null) {

			if (this.ioException != null) {
				throw this.ioException;
			}

			requestReadBufferFromBroker();

			if (this.dataBuffer == null) {
				return null;
			}

			if (this.decompressor != null) {
				this.dataBuffer = this.decompressor.decompress(this.dataBuffer);
			}
		}

		final T nextRecord = this.deserializer.readData(target, this.dataBuffer);

		if (this.dataBuffer.remaining() == 0) {
			releasedConsumedReadBuffer();
		}

		return nextRecord;
	}