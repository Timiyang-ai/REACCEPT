@Override
	public List<Location> getLocationsHavingAllTags(List<LocationTag> tags) {
		tags.removeAll(Collections.singleton(null));
		
		DetachedCriteria numberOfMatchingTags = DetachedCriteria.forClass(Location.class, "alias").createAlias("alias.tags",
		    "locationTag").add(Restrictions.in("locationTag.locationTagId", getLocationTagIds(tags))).setProjection(
		    Projections.rowCount()).add(Restrictions.eqProperty("alias.locationId", "outer.locationId"));
		
		return sessionFactory.getCurrentSession().createCriteria(Location.class, "outer").add(
		    Restrictions.eq("retired", false)).add(Subqueries.eq((long) tags.size(), numberOfMatchingTags)).list();
	}