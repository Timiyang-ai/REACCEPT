public static OsmUser getInstance(StoreReader sr, StoreClassRegister scr) {
		return getInstance(sr.readString(), sr.readInteger());
	}