@SuppressWarnings("unchecked")
	public List<Order> getActiveOrders(Patient patient, OrderType orderType, CareSetting careSetting, Date asOfDate) {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Order.class);
		
		if (orderType != null) {
			crit.add(Restrictions.eq("orderType", orderType));
		}
		
		crit.add(Restrictions.eq("patient", patient));
		if (careSetting != null) {
			crit.add(Restrictions.eq("careSetting", careSetting));
		}
		
		//See javadocs on OrderService#getActiveOrders for the description of an active Order
		crit.add(Restrictions.ne("action", Action.DISCONTINUE));
		crit.add(Restrictions.eq("voided", false));
		
		Criterion startDateEqualToOrBeforeAsOfDate = Restrictions.le("startDate", asOfDate);
		crit.add(startDateEqualToOrBeforeAsOfDate);
		
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