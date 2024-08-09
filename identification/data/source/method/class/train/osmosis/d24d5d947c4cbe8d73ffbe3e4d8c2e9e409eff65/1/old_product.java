public static OsmUser getInstance(StoreReader sr, StoreClassRegister scr) {
		if (sr.readBoolean()) {
			return getInstance(sr.readString(), sr.readInteger());
		} else {
			return NO_USER;
		}
	}