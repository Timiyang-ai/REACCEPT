public String getCommaSeparatedPatientIds() {
		StringBuilder sb = new StringBuilder();
		for (CohortMembership member : getMembers()) {
			sb.append(member.getPatient().getPatientId());
			sb.append(",");
		}
		return sb.toString();
	}