public boolean contains(double d) {
		if (low != null) {
			if (closedLow) {
				if (d < low) {
					return false;
				}
			} else {
				//unreachable code as closedLow is never set to false anywhere
				if (d <= low) {
					return false;
				}
			}
		}
		if (high != null) {
			if (closedHigh) {
				//unreachable code as closedHigh is never set to true anywhere
				if (d > high) {
					return false;
				}
			} else {
				if (d >= high) {
					return false;
				}
			}
		}
		return true;
	}