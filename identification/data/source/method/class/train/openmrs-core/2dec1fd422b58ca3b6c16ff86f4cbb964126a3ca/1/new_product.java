@Override
	public List<Order> getOrdersByPatient(Patient patient) throws APIException {
		return getOrdersByPatient(patient, false);
	}