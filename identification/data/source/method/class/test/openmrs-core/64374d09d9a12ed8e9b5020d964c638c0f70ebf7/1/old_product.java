@Override
	public VisitAttributeType saveVisitAttributeType(VisitAttributeType visitAttributeType) {
		getCurrentSession().saveOrUpdate(visitAttributeType);
		return visitAttributeType;
	}