public static Cohort subtract(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		ret.setName("(" + a.getName() + " - " + b.getName() + ")");
		if (a != null) {
			ret.getMemberIds().addAll(a.getMemberIds());
			if (b != null)
				ret.getMemberIds().removeAll(b.getMemberIds());
		}
		return ret;
	}