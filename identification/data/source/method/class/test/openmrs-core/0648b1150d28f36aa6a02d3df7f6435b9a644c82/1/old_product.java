public static Cohort intersect(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		ret.setName("(" + a.getName() + " * " + b.getName() + ")");
		if (a != null && b != null) {
			ret.getMemberIds().addAll(a.getMemberIds());
			ret.getMemberIds().retainAll(b.getMemberIds());
		}
		return ret;
	}