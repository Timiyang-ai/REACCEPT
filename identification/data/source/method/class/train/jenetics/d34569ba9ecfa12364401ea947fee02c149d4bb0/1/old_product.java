public static int[] subset(final int n, final int a[], final Random random) {
		requireNonNull(random, "Random");
		requireNonNull(a, "Sub set array");

		final int k = a.length;
		checkSubSet(n, k);

		// Early return.
		if (a.length == n) {
			for (int i = 0; i < k; ++i) {
				a[i] = i;
			}
			return a;
		}

		// (A): Initialize a[i] to "zero" point for bin Ri.
		for (int i = 0; i < k; ++i) {
			a[i] = (i*n)/k;
		}

		// (B)
		int l = 0;
		int x = 0;
		for (int c = 0; c < k; ++c) {
			do {
				// Choose random x;
				x = 1 + nextX(random, n - 1);

				// determine range Rl;
				l = (x*k - 1)/n;
			} while (a[l] >= x); // accept or reject.

			++a[l];
		}
		int s = k;

		// (C) Move a[i] of nonempty bins to the left.
		int m = 0;
		int p = 0;
		for (int i = 0; i < k; ++i) {
			if (a[i] == (i*n)/k) {
				a[i] = 0;
			} else {
				++p;
				m = a[i];
				a[i] = 0;
				a[p - 1] = m;
			}
		}

		// (D) Determine l, set up space for Bl.
		int ds = 0;
		for (; p > 0; --p) {
			l = 1 + (a[p - 1]*k - 1)/n;
			ds = a[p - 1] - ((l - 1)*n)/k;
			a[p - 1] = 0;
			a[s - 1] = l;
			s -= ds;
		}

		// (E) If a[l] != 0, a new bin is to be processed.
		int r = 0;
		int m0 = 0;
		for (int ll = 1; ll <= k; ++ll) {
			l = k + 1 - ll;

			if (a[l - 1] != 0) {
				r = l;
				m0 = 1 + ((a[l - 1] - 1)*n)/k;
				m = (a[l-1]*n)/k - m0 + 1;
			}

			// (F) Choose a random x.
			x = m0 + nextX(random, m - 1);
			int i = l + 1;

			// (G) Check x against previously entered elements in bin;
			//     increment x as it jumps over elements <= x.
			while (i <= r && x >= a[i - 1]) {
				++x;
				a[i- 2] = a[i - 1];
				++i;
			}

			a[i - 2] = x;
			--m;
		}

		return a;
	}