private static boolean isEvent(ByteBuffer buffer, Class<?> eventClass, ClassLoader classLoader) throws IOException {
		if (buffer.remaining() < 4) {
			throw new IOException("Incomplete event");
		}

		final int bufferPos = buffer.position();
		final ByteOrder bufferOrder = buffer.order();
		buffer.order(ByteOrder.BIG_ENDIAN);

		try {
			int type = buffer.getInt();

			switch (type) {
				case END_OF_PARTITION_EVENT:
					return eventClass.equals(EndOfPartitionEvent.class);
				case CHECKPOINT_BARRIER_EVENT:
					return eventClass.equals(CheckpointBarrier.class);
				case END_OF_SUPERSTEP_EVENT:
					return eventClass.equals(EndOfSuperstepEvent.class);
				case CANCEL_CHECKPOINT_MARKER_EVENT:
					return eventClass.equals(CancelCheckpointMarker.class);
				case OTHER_EVENT:
					try {
						final DataInputDeserializer deserializer = new DataInputDeserializer(buffer);
						final String className = deserializer.readUTF();

						final Class<? extends AbstractEvent> clazz;
						try {
							clazz = classLoader.loadClass(className).asSubclass(AbstractEvent.class);
						}
						catch (ClassNotFoundException e) {
							throw new IOException("Could not load event class '" + className + "'.", e);
						}
						catch (ClassCastException e) {
							throw new IOException("The class '" + className + "' is not a valid subclass of '"
								+ AbstractEvent.class.getName() + "'.", e);
						}
						return eventClass.equals(clazz);
					}
					catch (Exception e) {
						throw new IOException("Error while deserializing or instantiating event.", e);
					}
				default:
					throw new IOException("Corrupt byte stream for event");
			}
		}
		finally {
			buffer.order(bufferOrder);
			// restore the original position in the buffer (recall: we only peak into it!)
			buffer.position(bufferPos);
		}
	}