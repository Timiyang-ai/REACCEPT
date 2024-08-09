public static MosaicDescriptor readFrom(final Deserializer deserializer, final String label) {
		return new MosaicDescriptor(deserializer.readString(label, MAX_DESCRIPTION_LENGTH));
	}