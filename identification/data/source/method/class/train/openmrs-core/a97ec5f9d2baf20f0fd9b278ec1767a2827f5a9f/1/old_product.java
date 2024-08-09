public String toString() {
		StringBuffer ret = new StringBuffer();
		if (low != null && low.doubleValue() != Double.NEGATIVE_INFINITY) {
			ret.append(">");
			if (closedLow) {
				ret.append("=");
			}
			ret.append(" " + Format.format(low));
			if (high != null && high.doubleValue() != Double.NEGATIVE_INFINITY) {
				//BUG: should not append this if high is also infinite
				ret.append(" and ");
			}
		}
		if (high != null && high.doubleValue() != Double.POSITIVE_INFINITY) {
			ret.append("<");
			if (closedHigh) {
				ret.append("=");
			}
			ret.append(" " + Format.format(high));
		}
		return ret.toString();
	}