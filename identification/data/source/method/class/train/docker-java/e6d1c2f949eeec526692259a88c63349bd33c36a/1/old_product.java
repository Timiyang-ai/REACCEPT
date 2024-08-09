public static Link parse(final String serialized)
	{
		try {
			final String[] parts = serialized.split(":");
			switch (parts.length) {
			case 2: {
				return new Link(parts[0], parts[1]);
			}
			default: {
				throw new RuntimeException("Error parsing Link '" + serialized + "'");
			}
			}
		} catch (final Exception e) {
			throw new RuntimeException("Error parsing Link '" + serialized + "'");
		}
	}