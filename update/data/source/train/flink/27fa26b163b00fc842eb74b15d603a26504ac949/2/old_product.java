private T deserializeNextRecord() throws IOException {

		if (this.bufferedRecord != null) {
			final T record = this.bufferedRecord;
			this.bufferedRecord = null;
			return record;
		}

		if (this.uncompressedDataBuffer == null) {

			synchronized (this.synchronisationObject) {

				if (this.ioException != null) {
					throw this.ioException;
				}

				requestReadBuffersFromBroker();
			}

			if (this.uncompressedDataBuffer == null) {
				return null;
			}

			if (this.decompressor != null) {
				this.decompressor.decompress();
			}
		}

		final T nextRecord = this.deserializationBuffer.readData(this.uncompressedDataBuffer);

		if (this.uncompressedDataBuffer.remaining() == 0) {
			releasedConsumedReadBuffer();
			this.bufferedRecord = nextRecord;
			return null;
		}

		return nextRecord;
	}