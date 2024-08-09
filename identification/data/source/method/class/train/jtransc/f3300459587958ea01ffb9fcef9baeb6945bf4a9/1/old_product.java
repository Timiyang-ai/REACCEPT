public static <T> ArrayList<T> list(Enumeration<T> e) {
		ArrayList<T> ts = new ArrayList<>();
		while (e.hasMoreElements()) {
			ts.add(e.nextElement());
		}
		return ts;
	}