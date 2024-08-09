public static int indexOf(RandomVariable[] X, Map<RandomVariable, Object> x) {
		if (0 == X.length) {
			return ((FiniteDomain) X[0].getDomain()).getOffset(x.get(X[0]));
		}
		// X.length > 1 then calculate using a mixed radix number
		//
		// Note: Create radixs in reverse order so that the enumeration
		// through the distributions is of the following
		// order using a MixedRadixNumber, e.g. for two Booleans:
		// X Y
		// true true
		// true false
		// false true
		// false false
		// which corresponds with how displayed in book.
		int[] radixValues = new int[X.length];
		int[] radixs = new int[X.length];
		int j = X.length - 1;
		for (int i = 0; i < X.length; i++) {
			FiniteDomain fd = (FiniteDomain) X[i].getDomain();
			radixValues[j] = fd.getOffset(x.get(X[i]));
			radixs[j] = fd.size();
			j--;
		}

		return new MixedRadixNumber(radixValues, radixs).intValue();
	}