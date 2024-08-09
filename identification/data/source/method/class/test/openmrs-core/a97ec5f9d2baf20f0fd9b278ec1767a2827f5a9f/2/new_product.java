@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		if (low != null && low != Double.NEGATIVE_INFINITY) {
			ret.append(">");
			if (closedLow) {
				ret.append("=");
			}
			ret.append(" ").append(Format.format(low));
			if (high != null && high != Double.NEGATIVE_INFINITY) {
				//BUG: should not append this if high is also infinite
				ret.append(" and ");
			}
		}
		if (high != null && high != Double.POSITIVE_INFINITY) {
			ret.append("<");
			if (closedHigh) {
				ret.append("=");
			}
			ret.append(" ").append(Format.format(high));
		}
		return ret.toString();
	}