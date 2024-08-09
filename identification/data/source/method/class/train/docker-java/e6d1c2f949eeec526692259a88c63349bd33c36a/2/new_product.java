public static Link parse(final String serialized) throws IllegalArgumentException
	{
		try {
			final String[] parts = serialized.split(":");
			switch (parts.length) {
			case 2: {
				String[] nameSplit = parts[0].split("/");
				String[] aliasSplit = parts[1].split("/");
				return new Link(nameSplit[nameSplit.length - 1], aliasSplit[aliasSplit.length - 1]);
			}
			default: {
				throw new IllegalArgumentException();
			}
			}
		} catch (final Exception e) {
			throw new IllegalArgumentException("Error parsing Link '" + serialized + "'");
		}
	}