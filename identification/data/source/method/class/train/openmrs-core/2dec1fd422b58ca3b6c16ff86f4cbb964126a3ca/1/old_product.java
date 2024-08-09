@Override
	public List<Order> getOrdersByPatient(Patient patient) throws APIException {
		if (patient == null)
			throw new APIException("Unable to get orders if I am not given a patient");
		
		List<Patient> patients = new Vector<Patient>();
		patients.add(patient);
		
		return getOrders(Order.class, patients, null, null, null, null, null, null);
	}