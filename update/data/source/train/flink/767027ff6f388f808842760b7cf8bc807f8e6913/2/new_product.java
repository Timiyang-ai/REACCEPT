private static boolean isEvent(ByteBuffer buffer, Class<?> eventClass) throws IOException {
		if (buffer.remaining() < 4) {
			throw new IOException("Incomplete event");
		}

		final int bufferPos = buffer.position();
		final ByteOrder bufferOrder = buffer.order();
		buffer.order(ByteOrder.BIG_ENDIAN);

		try {
			int type = buffer.getInt();

			if (eventClass.equals(EndOfPartitionEvent.class)) {
				return type == END_OF_PARTITION_EVENT;
			} else if (eventClass.equals(CheckpointBarrier.class)) {
				return type == CHECKPOINT_BARRIER_EVENT;
			} else if (eventClass.equals(EndOfSuperstepEvent.class)) {
				return type == END_OF_SUPERSTEP_EVENT;
			} else if (eventClass.equals(CancelCheckpointMarker.class)) {
				return type == CANCEL_CHECKPOINT_MARKER_EVENT;
			} else {
				throw new UnsupportedOperationException("Unsupported eventClass = " + eventClass);
			}
		}
		finally {
			buffer.order(bufferOrder);
			// restore the original position in the buffer (recall: we only peak into it!)
			buffer.position(bufferPos);
		}
	}