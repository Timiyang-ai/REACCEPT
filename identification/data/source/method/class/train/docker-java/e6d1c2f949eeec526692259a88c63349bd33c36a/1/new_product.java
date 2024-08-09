public static Link parse(final String serialized) throws IllegalArgumentException
	{
		try {
			final String[] parts = serialized.split(":");
			switch (parts.length) {
			case 2: {
				return new Link(parts[0], parts[1]);
			}
			default: {
				throw new IllegalArgumentException();
			}
			}
		} catch (final Exception e) {
			throw new IllegalArgumentException("Error parsing Link '" + serialized + "'");
		}
	}