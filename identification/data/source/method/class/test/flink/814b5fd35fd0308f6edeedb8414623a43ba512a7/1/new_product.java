public boolean endsWith(final BinaryString suffix) {
		ensureMaterialized();
		suffix.ensureMaterialized();
		return matchAt(suffix, binarySection.sizeInBytes - suffix.binarySection.sizeInBytes);
	}