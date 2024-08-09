private static <T> int dominance(
		final T u,
		final T v,
		final int dimension,
		final ElementComparator<? super T> comparator
	) {
		boolean udominated = false;
		boolean vdominated = false;

		for (int i = 0; i < dimension; ++i) {
			final int cmp = comparator.compare(u, v, i);

			if (cmp > 0) {
				udominated = true;
				if (vdominated) {
					return 0;
				}
			} else if (cmp < 0) {
				vdominated = true;
				if (udominated) {
					return 0;
				}
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