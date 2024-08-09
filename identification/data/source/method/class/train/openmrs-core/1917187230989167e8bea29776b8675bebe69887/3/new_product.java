public static Cohort subtract(Cohort a, Cohort b) {
		Cohort ret = new Cohort();
		if (a != null) {
			ret.getMemberships().addAll(a.getMemberships());
			if (b != null) {
				ret.getMemberships().removeAll(b.getMemberships());
				ret.setName("(" + a.getName() + " - " + b.getName() + ")");
			}
		}
		return ret;
	}