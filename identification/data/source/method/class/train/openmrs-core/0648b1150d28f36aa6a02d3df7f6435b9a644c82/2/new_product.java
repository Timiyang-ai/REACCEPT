public static Cohort intersect(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		ret.setName("(" + (a == null ? "NULL" : a.getName()) + " * " + (b == null ? "NULL" : b.getName()) + ")");
		if (a != null && b != null) {
			ret.getMembers().addAll(a.getMembers());
			ret.getMembers().retainAll(b.getMembers());
		}
		return ret;
	}