private T deserializeNextRecord(final T target) throws IOException {

		if (this.uncompressedDataBuffer == null) {

			if (this.ioException != null) {
				throw this.ioException;
			}

			requestReadBuffersFromBroker();

			if (this.uncompressedDataBuffer == null) {
				return null;
			}

			if (this.decompressor != null) {
				this.decompressor.decompress();
			}
		}

		final T nextRecord = this.deserializationBuffer.readData(target, this.uncompressedDataBuffer);

		if (this.uncompressedDataBuffer.remaining() == 0) {
			releasedConsumedReadBuffer();
		}

		return nextRecord;
	}