@Override
	public String toString() {
		String ret = "";
		ret += encounterRoleId == null ? "(no ID) " : encounterRoleId.toString() + " ";
		return "Encounter: [" + ret + "]";
	}