public static Cohort union(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		if (a != null) {
			ret.getMembers().addAll(a.getMembers());
		}
		if (b != null) {
			ret.getMembers().addAll(b.getMembers());
		}
		if (a != null && b != null) {
			ret.setName("(" + a.getName() + " + " + b.getName() + ")");
		}
		return ret;
	}