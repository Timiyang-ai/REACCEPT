@Override
	public Date getAutoExpireDate(DrugOrder drugOrder) {
		if (drugOrder.getDuration() == null || drugOrder.getDurationUnits() == null) {
			return null;
		}
		if (drugOrder.getNumRefills() != null && drugOrder.getNumRefills() > 0) {
			return null;
		}
		String durationCode = ISO8601Duration.getCode(drugOrder.getDurationUnits());
		if (durationCode == null) {
			return null;
		}
		ISO8601Duration iso8601Duration = new ISO8601Duration(drugOrder.getDuration(), durationCode);
		return iso8601Duration.addToDate(drugOrder.getEffectiveStartDate(), drugOrder.getFrequency());
	}