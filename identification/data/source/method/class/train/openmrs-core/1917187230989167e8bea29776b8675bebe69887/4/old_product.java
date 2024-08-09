public static Cohort union(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		if (a != null) {
			ret.getMemberIds().addAll(a.getMemberIds());
		}
		if (b != null) {
			ret.getMemberIds().addAll(b.getMemberIds());
		}
		if (a != null && b != null) {
			ret.setName("(" + a.getName() + " + " + b.getName() + ")");
		}
		return ret;
	}