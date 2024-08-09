private CloseableInputProvider<KeyValuePair<Key, Value>> obtainInput()
	{	
		// obtain the MemoryManager of the TaskManager
		final MemoryManager memoryManager = getEnvironment().getMemoryManager();
		// obtain the IOManager of the TaskManager
		final IOManager ioManager = getEnvironment().getIOManager();

		// obtain input key type
		final Class<Key> keyClass = stub.getFirstInKeyType();
		// obtain input value type
		final Class<Value> valueClass = stub.getFirstInValueType();

		// obtain key serializer
		final SerializationFactory<Key> keySerialization = new WritableSerializationFactory<Key>(keyClass);
		// obtain value serializer
		final SerializationFactory<Value> valSerialization = new WritableSerializationFactory<Value>(valueClass);

		// obtain grouped iterator defined by local strategy
		switch (config.getLocalStrategy()) {

		// local strategy is NONE
		// input is already grouped, an iterator that wraps the reader is
		// created and returned
		case SELF_NESTEDLOOP:
			// iterator wraps input reader
			Iterator<KeyValuePair<Key, Value>> iter = new NepheleReaderIterator<KeyValuePair<Key,Value>>(this.reader);			
			return new SimpleCloseableInputProvider<KeyValuePair<Key,Value>>(iter);

			// local strategy is SORT
			// The input is grouped using a sort-merge strategy.
			// An iterator on the sorted pairs is created and returned.
		case SORT_SELF_NESTEDLOOP:
			// create a key comparator
			final Comparator<Key> keyComparator = new Comparator<Key>() {
				@Override
				public int compare(Key k1, Key k2) {
					return k1.compareTo(k2);
				}
			};

			try {
				// instantiate a sort-merger
				SortMerger<Key, Value> sortMerger = new UnilateralSortMerger<Key, Value>(memoryManager, ioManager,
					(long)(this.availableMemory * (1.0 - MEMORY_SHARE_RATIO)), this.maxFileHandles, keySerialization,
					valSerialization, keyComparator, reader, this, this.spillThreshold);
				// obtain and return a grouped iterator from the sort-merger
				return sortMerger;
			}
			catch (MemoryAllocationException mae) {
				throw new RuntimeException(
					"MemoryManager is not able to provide the required amount of memory for SelfMatchTask", mae);
			}
			catch (IOException ioe) {
				throw new RuntimeException("IOException caught when obtaining SortMerger for SelfMatchTask", ioe);
			}
			
		default:
			throw new RuntimeException("Invalid local strategy provided for SelfMatchTask: " +
				config.getLocalStrategy());
		}

	}