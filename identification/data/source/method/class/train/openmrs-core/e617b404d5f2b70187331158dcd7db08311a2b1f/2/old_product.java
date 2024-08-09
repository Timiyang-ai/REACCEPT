public List<Order> getOrdersByPatient(Patient patient, boolean includeVoided) throws APIException {
		if (patient == null)
			throw new APIException("Unable to get orders if I am not given a patient");
		
		List<Patient> patients = new ArrayList<Patient>();
		patients.add(patient);
		
		return getOrders(Order.class, patients, null, includeVoided ? ORDER_STATUS.ANY : ORDER_STATUS.NOTVOIDED, null, null,
		    null);
	}