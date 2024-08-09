private static <C extends Comparable<? super C>> void
	check(final C min, final C max, final int nclasses)
	{
		requireNonNull(min, "Minimum");
		requireNonNull(max, "Maximum");
		if (min.compareTo(max) >= 0) {
			throw new IllegalArgumentException(String.format(
					"Min must be smaller than max: %s < %s failed.", min, max
				));
		}
		if (nclasses < 2) {
			throw new IllegalArgumentException(String.format(
				"nclasses should be < 2, but was %s.", nclasses
			));
		}
	}