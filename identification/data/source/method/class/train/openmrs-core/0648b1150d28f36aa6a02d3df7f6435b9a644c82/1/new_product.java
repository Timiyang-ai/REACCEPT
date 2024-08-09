public static Cohort intersect(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		ret.setName("(" + (a == null ? "NULL" : a.getName()) + " * " + (b == null ? "NULL" : b.getName()) + ")");
		if (a != null && b != null) {
			ret.getMemberships().addAll(a.getMemberships());
			ret.getMemberships().retainAll(b.getMemberships());
		}
		return ret;
	}