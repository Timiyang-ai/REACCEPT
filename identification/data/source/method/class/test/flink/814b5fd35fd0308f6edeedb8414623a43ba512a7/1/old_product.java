public boolean endsWith(final BinaryString suffix) {
		ensureMaterialized();
		suffix.ensureMaterialized();
		return matchAt(suffix, sizeInBytes - suffix.sizeInBytes);
	}