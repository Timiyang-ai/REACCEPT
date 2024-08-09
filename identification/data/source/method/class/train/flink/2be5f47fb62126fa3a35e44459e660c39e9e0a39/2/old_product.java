public void releaseAll(Object owner) {
		if (owner == null) {
			return;
		}

		// -------------------- BEGIN CRITICAL SECTION -------------------
		synchronized (lock) {
			if (isShutDown) {
				throw new IllegalStateException("Memory manager has been shut down.");
			}

			// get all segments
			final Set<MemorySegment> segments = allocatedSegments.remove(owner);

			// all segments may have been freed previously individually
			if (segments == null || segments.isEmpty()) {
				return;
			}

			// free each segment
			if (isPreAllocated) {
				for (MemorySegment seg : segments) {
					memoryPool.returnSegmentToPool(seg);
				}
			}
			else {
				for (MemorySegment seg : segments) {
					seg.free();
				}
				numNonAllocatedPages += segments.size();
			}

			segments.clear();
		}
		// -------------------- END CRITICAL SECTION -------------------
	}