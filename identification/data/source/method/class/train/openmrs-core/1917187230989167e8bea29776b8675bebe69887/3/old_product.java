public static Cohort subtract(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		if (a != null) {
			ret.getMembers().addAll(a.getMembers());
			if (b != null) {
				ret.getMembers().removeAll(b.getMembers());
				ret.setName("(" + a.getName() + " - " + b.getName() + ")");
			}
		}
		return ret;
	}