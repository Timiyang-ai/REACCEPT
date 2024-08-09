public boolean sameState(final Quantile other) {
		return eq(_quantile, other._quantile) &&
			eq(_dn, other._dn) &&
			eq(_n, other._n) &&
			eq(_nn, other._nn) &&
			eq(_q, other._q);
	}