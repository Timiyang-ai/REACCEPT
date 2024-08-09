public static Cohort subtract(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		if (a != null) {
			ret.getMemberIds().addAll(a.getMemberIds());
			if (b != null) {
				ret.getMemberIds().removeAll(b.getMemberIds());
				ret.setName("(" + a.getName() + " - " + b.getName() + ")");
			}
		}
		return ret;
	}