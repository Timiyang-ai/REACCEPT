private static Entry getEntry() {
		final Entry e = THREAD_LOCAL_ENTRY.get();
		return e != null ? e : ENTRY.get();
	}