public static Cohort union(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		if (a != null) {
			ret.getMemberships().addAll(a.getMemberships());
		}
		if (b != null) {
			ret.getMemberships().addAll(b.getMemberships());
		}
		if (a != null && b != null) {
			ret.setName("(" + a.getName() + " + " + b.getName() + ")");
		}
		return ret;
	}