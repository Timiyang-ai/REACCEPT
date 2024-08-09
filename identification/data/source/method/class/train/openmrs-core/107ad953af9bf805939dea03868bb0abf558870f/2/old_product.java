@SuppressWarnings("unchecked")
	public List<Obs> getObservations(Patient who, Aggregation aggregation,
			Concept question, Constraint constraint) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Obs.class);

		criteria.add(Restrictions.eq("patient", who));
		criteria.add(Restrictions.eq("concept", question));
		criteria.add(Restrictions.eq("voided", false));

		DateConstraint dateConstraint = (DateConstraint) constraint;
		final String OBS_DATETIME = "obsDatetime";
		Criterion dateCriterion = null;
		GregorianCalendar d0, d1, d2;
		if (dateConstraint != null) {
			int dateComparison = dateConstraint.getComparison();
			switch (dateComparison) {
			case DateConstraint.EQUAL:
				dateCriterion = Restrictions.eq(OBS_DATETIME, dateConstraint
						.getDate());
				break;
			case DateConstraint.NOT_EQUAL:
				dateCriterion = Restrictions.ne(OBS_DATETIME, dateConstraint
						.getDate());
				break;
			case DateConstraint.WITHIN:
			case DateConstraint.NOT_WITHIN:
				dateCriterion = Restrictions.between(OBS_DATETIME,
						dateConstraint.getDate(), dateConstraint
								.getSecondDate());
				if (dateComparison == DateConstraint.NOT_WITHIN)
					dateCriterion = Restrictions.not(dateCriterion);
				break;
			case DateConstraint.WITHIN_PRECEDING:
			case DateConstraint.NOT_WITHIN_PRECEDING:
				d1 = new GregorianCalendar();
				d2 = new GregorianCalendar();
				d2.setTime(dateConstraint.getDate());
				d1.setTimeInMillis(d2.getTimeInMillis()
						- dateConstraint.getDuration().getDurationInMillis());
				dateCriterion = Restrictions.between(OBS_DATETIME,
						d1.getTime(), d2.getTime());
				if (dateComparison == DateConstraint.NOT_WITHIN_PRECEDING)
					dateCriterion = Restrictions.not(dateCriterion);
				break;
			case DateConstraint.WITHIN_FOLLOWING:
			case DateConstraint.NOT_WITHIN_FOLLOWING:
				d1 = new GregorianCalendar();
				d2 = new GregorianCalendar();
				d1.setTime(dateConstraint.getDate());
				d2.setTimeInMillis(d1.getTimeInMillis()
						+ dateConstraint.getDuration().getDurationInMillis());
				dateCriterion = Restrictions.between(OBS_DATETIME,
						d1.getTime(), d2.getTime());
				if (dateComparison == DateConstraint.NOT_WITHIN_FOLLOWING)
					dateCriterion = Restrictions.not(dateCriterion);
				break;
			case DateConstraint.WITHIN_SURROUNDING:
			case DateConstraint.NOT_WITHIN_SURROUNDING:
				d0 = new GregorianCalendar();
				d0.setTime(dateConstraint.getDate());
				d1 = new GregorianCalendar();
				d2 = new GregorianCalendar();
				long delta = dateConstraint.getDuration().getDurationInMillis() / 2;
				d1.setTimeInMillis(d0.getTimeInMillis() - delta);
				d2.setTimeInMillis(d0.getTimeInMillis() + delta);
				dateCriterion = Restrictions.between(OBS_DATETIME,
						d1.getTime(), d2.getTime());
				if (dateComparison == DateConstraint.NOT_WITHIN_SURROUNDING)
					dateCriterion = Restrictions.not(dateCriterion);
				break;
			case DateConstraint.WITHIN_PAST:
			case DateConstraint.NOT_WITHIN_PAST:
				d1 = new GregorianCalendar();
				d2 = new GregorianCalendar();
				d2.setTime(new Date());
				d1.setTimeInMillis(d1.getTimeInMillis()
						- dateConstraint.getDuration().getDurationInMillis());
				dateCriterion = Restrictions.between(OBS_DATETIME,
						d1.getTime(), d2.getTime());
				if (dateComparison == DateConstraint.NOT_WITHIN_PAST)
					dateCriterion = Restrictions.not(dateCriterion);
				break;
			case DateConstraint.WITHIN_SAME_DAY_AS:
			case DateConstraint.NOT_WITHIN_SAME_DAY_AS:
				d0 = new GregorianCalendar();
				d0.setTime(dateConstraint.getDate());
				d0.set(d0.get(Calendar.YEAR), d0.get(Calendar.MONTH), d0
						.get(Calendar.DATE), 0, 0, 0);
				d1 = new GregorianCalendar();
				d2 = new GregorianCalendar();
				d1.setTimeInMillis(d0.getTimeInMillis() - 1);
				d2.setTimeInMillis(d0.getTimeInMillis() + 1440000);
				dateCriterion = Restrictions.between(OBS_DATETIME,
						d1.getTime(), d2.getTime());
				if (dateComparison == DateConstraint.NOT_WITHIN_SAME_DAY_AS)
					dateCriterion = Restrictions.not(dateCriterion);
				break;
			case DateConstraint.AFTER:
				dateCriterion = Restrictions.gt(OBS_DATETIME, dateConstraint
						.getDate());
				break;
			case DateConstraint.NOT_AFTER:
				dateCriterion = Restrictions.le(OBS_DATETIME, dateConstraint
						.getDate());
				break;
			case DateConstraint.BEFORE:
				dateCriterion = Restrictions.lt(OBS_DATETIME, dateConstraint
						.getDate());
				break;
			case DateConstraint.NOT_BEFORE:
				dateCriterion = Restrictions.ge(OBS_DATETIME, dateConstraint
						.getDate());
				break;
			}
		}

		// Aggregation.EXIST - true if any results found
		// Aggregation.MEDIAN
		// Aggregation.STDDEV
		// Aggregation.VARIANCE
		// Aggregation.ANY - true if any results true, false for empty list or if any results non-boolean
		// Aggregation.ALL - true if all results true, null if any results non-boolean, true for empty list
		// Aggregation.NO - true if all results false
		// Aggregation.N_MINIMUM
		// Aggregation.N_MAXIMUM
		
		// TODO: figure out a way to return average, count, sum as "pseudo" obs
		Projection projection = null;
		boolean singleNumericResult = false;
		if (aggregation != null) {
			switch (aggregation.getType()) {
			case Aggregation.ALL:
				break;
			case Aggregation.AVERAGE:
				projection = Projections.avg("valueNumeric");
				singleNumericResult = true;
				break;
			case Aggregation.COUNT:
				projection = Projections.rowCount();
				singleNumericResult = true;
				break;
			case Aggregation.MAXIMUM:
				DetachedCriteria maxObs = DetachedCriteria.forClass(
						Obs.class).add(Restrictions.eq("patient", who)).add(
						Restrictions.eq("concept", question));
				if (dateCriterion != null)
					maxObs = maxObs
						.add(dateCriterion);
				maxObs = maxObs.setProjection(
								Projections.max("valueNumeric"));
				if (dateCriterion != null)
					criteria.add(dateCriterion);
				criteria.add(
						Property.forName("valueNumeric").eq(maxObs));
				dateCriterion = null;
				break;
			case Aggregation.MINIMUM:
				DetachedCriteria minObs = DetachedCriteria.forClass(
						Obs.class).add(Restrictions.eq("patient", who)).add(
						Restrictions.eq("concept", question));
				if (dateCriterion != null)
					minObs = minObs
						.add(dateCriterion);
				minObs = minObs.setProjection(
								Projections.min("valueNumeric"));
				if (dateCriterion != null)
					criteria.add(dateCriterion);
				criteria.add(
						Property.forName("valueNumeric").eq(minObs));
				dateCriterion = null;
				break;
			case Aggregation.SUM:
				projection = Projections.sum("valueNumeric");
				singleNumericResult = true;
				break;
			case Aggregation.LATEST:
			case Aggregation.LAST:
				DetachedCriteria latestObs = DetachedCriteria.forClass(
						Obs.class).add(Restrictions.eq("patient", who)).add(
						Restrictions.eq("concept", question));
				if (dateCriterion != null)
					latestObs = latestObs
						.add(dateCriterion);
				latestObs = latestObs.setProjection(
								Projections.max("obsDatetime"));
				if (dateCriterion != null)
					criteria.add(dateCriterion);
				criteria.add(
						Property.forName("obsDatetime").eq(latestObs));
				dateCriterion = null;
				break;
			case Aggregation.EARLIEST:
			case Aggregation.FIRST:
				DetachedCriteria earliestObs = DetachedCriteria.forClass(
						Obs.class).add(Restrictions.eq("patient", who)).add(
						Restrictions.eq("concept", question));
				if (dateCriterion != null)
					earliestObs = earliestObs
						.add(dateCriterion);
				earliestObs = earliestObs.setProjection(
								Projections.min("obsDatetime"));
				if (dateCriterion != null)
					criteria.add(dateCriterion);
				criteria.add(
						Property.forName("obsDatetime").eq(earliestObs));
				dateCriterion = null;
			}
		}

		if (dateCriterion != null)
			criteria.add(dateCriterion);
		criteria.addOrder(Order.asc(OBS_DATETIME));
		if (projection != null)
			criteria.setProjection(projection);

		List<Obs> result;
		
		if (singleNumericResult) {
			Object numericResult = criteria.uniqueResult();
			Double resultValue = Double.parseDouble(numericResult.toString());
			result = new Vector<Obs>();
			Obs obs = new Obs();
			obs.setConcept(question);
			obs.setObsDatetime(new Date());
			obs.setValueNumeric(resultValue);
			result.add(obs);
		} else {
		
			result = criteria.list();

			if (aggregation != null) {
				switch (aggregation.getType()) {
				case Aggregation.N_LAST:
				case Aggregation.N_LATEST:
					if (result.size() > aggregation.getN()) {
						List<Obs> tail = new Vector<Obs>();
						for (int i = result.size() - aggregation.getN(); i < result
								.size(); i++) {
							tail.add(result.get(i));
						}
						result = tail;
					}
					break;
				case Aggregation.N_FIRST:
				case Aggregation.N_EARLIEST:
					if (result.size() > aggregation.getN()) {
						List<Obs> head = new Vector<Obs>();
						for (int i = 0; i < aggregation.getN(); i++) {
							head.add(result.get(i));
						}
						result = head;
					}
					break;
				}
			}
			
		}
		return result;
	}