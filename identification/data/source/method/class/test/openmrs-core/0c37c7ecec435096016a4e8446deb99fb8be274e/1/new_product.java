@Override
	public Condition saveCondition(Condition condition) {
		Date endDate = condition.getEndDate() != null ? condition.getEndDate() : new Date();
		if (condition.getEndReason() != null) {
			condition.setEndDate(endDate);
		}
		
		Condition existingCondition = getConditionByUuid(condition.getUuid());
		if (condition.equals(existingCondition)) {
			return existingCondition;
		}
		if (existingCondition == null) {
			return conditionDAO.saveCondition(condition);
		}

		condition = Condition.newInstance(condition);
		condition.setPreviousVersion(existingCondition);
		if (existingCondition.getClinicalStatus().equals(condition.getClinicalStatus())) {
			existingCondition.setVoided(true);
			conditionDAO.saveCondition(existingCondition);
			return conditionDAO.saveCondition(condition);
		}
		Date onSetDate = condition.getOnsetDate() != null ? condition.getOnsetDate() : new Date();
		existingCondition.setEndDate(onSetDate);
		conditionDAO.saveCondition(existingCondition);
		condition.setOnsetDate(onSetDate);

		return conditionDAO.saveCondition(condition);
	}