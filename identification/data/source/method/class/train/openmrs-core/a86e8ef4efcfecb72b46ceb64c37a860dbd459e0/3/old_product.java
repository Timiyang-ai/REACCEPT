public String getCommaSeparatedPatientIds() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Integer> i = getMemberIds().iterator(); i.hasNext();) {
			sb.append(i.next());
			if (i.hasNext()) {
				sb.append(",");
			}
		}
		return sb.toString();
	}