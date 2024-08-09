@SuppressWarnings("unchecked")
	public List<Order> getActiveOrders(Patient patient, List<OrderType> orderTypes, CareSetting careSetting, Date asOfDate) {
		Criteria crit = createOrderCriteria(patient, careSetting, orderTypes, false, false);
		
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