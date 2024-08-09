@Override
	public BinRange[] getNormalizedRanges(
			final NumericData range ) {
		// if the range is a single value, clamp at -180, 180
		if (checkIfDoublesEqual(
				range.getMin(),
				range.getMax())) {

			return super.getNormalizedRanges(range);
		}
		// if its a range, treat values outside of (-180,180) as possible date
		// line crossing
		final double normalizedMin = getNormalizedLongitude(range.getMin());
		final double normalizedMax = getNormalizedLongitude(range.getMax());

		// If the normalized max is less than normalized min, the range
		// crosses the date line
		if (normalizedMax < normalizedMin) {

			return new BinRange[] {
				new BinRange(
						-180,
						normalizedMax),
				new BinRange(
						normalizedMin,
						180)
			};
		}

		return new BinRange[] {
			new BinRange(
					normalizedMin,
					normalizedMax)
		};
	}