private void runStreamed(MemoryManager memoryManager, IOManager ioManager,
			RecordReader<KeyValuePair<Key, Value>> innerReader, final RecordReader<KeyValuePair<Key, Value>> outerReader)
			throws RuntimeException {

		// obtain streaming iterator for outer side
		// streaming is achieved by simply wrapping the input reader of the outer side
		LastRepeatableIterator<KeyValuePair<Key, Value>> outerInput = new LastRepeatableIterator<KeyValuePair<Key, Value>>() {

			SerializationCopier<KeyValuePair<Key, Value>> copier = new SerializationCopier<KeyValuePair<Key,Value>>();
			
			KeyValuePairDeserializer<Key, Value> deserializer = 
				new KeyValuePairDeserializer<Key, Value>(stub.getSecondInKeyType(), stub.getSecondInValueType());
			
			@Override
			public boolean hasNext() {
				return outerReader.hasNext();
			}

			@Override
			public KeyValuePair<Key, Value> next() {
				try {
					KeyValuePair<Key,Value> pair = outerReader.next();
					
					// serialize pair
					copier.setCopy(pair);
					
					return pair;
				} catch (IOException e) {
					throw new RuntimeException(e);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public KeyValuePair<Key, Value> repeatLast() {
				KeyValuePair<Key,Value> pair = deserializer.getInstance();
				copier.getCopy(pair);
				
				return pair;
			}
			
		};

		// obtain SpillingResettableIterator for inner side
		SpillingResettableIterator<KeyValuePair<Key, Value>> innerInput = null;
		try {
			innerInput = new SpillingResettableIterator<KeyValuePair<Key, Value>>(memoryManager, ioManager,
				innerReader, MEMORY_IO, new KeyValuePairDeserializer<Key, Value>(stub.getFirstInKeyType(), stub
					.getFirstInValueType()), this);
		} catch (MemoryAllocationException mae) {
			throw new RuntimeException("Unable to obtain SpillingResettable iterator for inner side.", mae);
		}

		// open spilling resettable iterator
		try {
			innerInput.open();
		} catch (ServiceException se) {
			throw new RuntimeException("Unable to open SpillingResettable iterator for inner side.", se);
		} catch (IOException ioe) {
			throw new RuntimeException("Unable to open SpillingResettable iterator for inner side.", ioe);
		} catch (InterruptedException ie) {
			throw new RuntimeException("Unable to open SpillingResettable iterator for inner side.", ie);
		}

		LOG.debug("Resetable iterator obtained: " + this.getEnvironment().getTaskName() + " ("
			+ (this.getEnvironment().getIndexInSubtaskGroup() + 1) + "/"
			+ this.getEnvironment().getCurrentNumberOfSubtasks() + ")");

		// open stub implementation
		stub.open();

		// read streamed iterator of outer side
		while (outerInput.hasNext()) {
			// get outer pair
			Pair outerPair = outerInput.next();

			// read spilled iterator of inner side
			while (innerInput.hasNext()) {
				// get inner pair
				Pair innerPair = innerInput.next();

				// call cross() method of CrossStub depending on local
				// strategy
				if (config.getLocalStrategy() == LocalStrategy.NESTEDLOOP_STREAMED_OUTER_SECOND) {
					// call stub with inner pair (first input) and outer pair (second input)
					stub.cross(innerPair.getKey(), innerPair.getValue(), outerPair.getKey(), outerPair.getValue(),
						output);
				} else {
					// call stub with inner pair (second input) and outer pair (first input)
					stub.cross(outerPair.getKey(), outerPair.getValue(), innerPair.getKey(), innerPair.getValue(),
						output);
				}
				
				outerPair = outerInput.repeatLast();
			}
			// reset spilling resettable iterator of inner side
			innerInput.reset();
		}

		// close stub implementation
		stub.close();

		// close spilling resettable iterator
		try {
			innerInput.close();
		} catch (ServiceException se) {
			throw new RuntimeException("Unable to close SpillingResettable iterator for inner side.", se);
		}
	}