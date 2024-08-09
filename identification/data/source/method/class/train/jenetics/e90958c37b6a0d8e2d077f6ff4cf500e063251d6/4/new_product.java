public static <V> int dominance(
		final V u,
		final V v,
		final int dimensions,
		final ElementComparator<? super V> comparator
	) {
		boolean udominated = false;
		boolean vdominated = false;

		for (int i = 0; i < dimensions; ++i) {
			final int cmp = comparator.compare(u, v, i);

			if (cmp > 0) {
				if (vdominated) {
					return 0;
				}
				udominated = true;
			} else if (cmp < 0) {
				if (udominated) {
					return 0;
				}
				vdominated = true;
			}
		}

		if (udominated == vdominated) {
			return 0;
		} else if (udominated) {
			return 1;
		} else {
			return -1;
		}
	}