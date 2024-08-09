@Override
	public Date getAutoExpireDate(DrugOrder drugOrder) {
		if (drugOrder.getDuration() == null || drugOrder.getDurationUnits() == null) {
			return null;
		}
		if (drugOrder.getNumRefills() != null && drugOrder.getNumRefills() > 0) {
			return null;
		}
		String durationCode = Duration.getCode(drugOrder.getDurationUnits());
		if (durationCode == null) {
			return null;
		}
		Duration duration = new Duration(drugOrder.getDuration(), durationCode);
		return duration.addToDate(drugOrder.getEffectiveStartDate(), drugOrder.getFrequency());
	}