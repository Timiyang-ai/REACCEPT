@SuppressWarnings("unchecked")
	public List<Order> getActiveOrders(Patient patient, OrderType orderType, CareSetting careSetting, Date asOfDate) {
		Criteria crit = createOrderCriteria(patient, careSetting, orderType, false, false);
		
		Disjunction dateStoppedAndAutoExpDateDisjunction = Restrictions.disjunction();
		Criterion stopAndAutoExpDateAreBothNull = Restrictions.and(Restrictions.isNull("dateStopped"), Restrictions
		        .isNull("autoExpireDate"));
		dateStoppedAndAutoExpDateDisjunction.add(stopAndAutoExpDateAreBothNull);
		
		Criterion autoExpireDateEqualToOrAfterAsOfDate = Restrictions.and(Restrictions.isNull("dateStopped"), Restrictions
		        .ge("autoExpireDate", asOfDate));
		dateStoppedAndAutoExpDateDisjunction.add(autoExpireDateEqualToOrAfterAsOfDate);
		
		dateStoppedAndAutoExpDateDisjunction.add(Restrictions.ge("dateStopped", asOfDate));
		
		crit.add(dateStoppedAndAutoExpDateDisjunction);
		
		return crit.list();
	}