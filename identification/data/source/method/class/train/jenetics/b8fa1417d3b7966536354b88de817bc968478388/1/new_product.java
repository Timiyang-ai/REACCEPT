public boolean sameState(final Quantile other) {
		return Objects.equals(_quantile, other._quantile) &&
			Arrays.equals(_dn, other._dn) &&
			Arrays.equals(_n, other._n) &&
			Arrays.equals(_nn, other._nn) &&
			Arrays.equals(_q, other._q);
	}