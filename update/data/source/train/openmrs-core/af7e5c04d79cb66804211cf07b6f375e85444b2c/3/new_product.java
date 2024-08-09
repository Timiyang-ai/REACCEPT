protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		newIdentifiers = new HashSet<PatientIdentifier>();
		Patient p = null;
		Integer id = null;
		
		if (Context.isAuthenticated()) {
			PatientService ps = Context.getPatientService();
			String patientId = request.getParameter("patientId");
			if (patientId != null) {
				try {
					id = Integer.valueOf(patientId);
					p = ps.getPatient(id);
				}
				catch (NumberFormatException numberError) {
					log.warn("Invalid patientId supplied: '" + patientId + "'", numberError);
				}
				catch (ObjectRetrievalFailureException noUserEx) {
					// continue
				}
			}
			
			if (p == null) {
				try {
					Person person = Context.getPersonService().getPerson(id);
					if (person != null)
						p = new Patient(person);
				}
				catch (ObjectRetrievalFailureException noPersonEx) {
					log.warn("There is no patient or person with id: '" + id + "'", noPersonEx);
					throw new ServletException("There is no patient or person with id: '" + id + "'");
				}
			}
		}
		
		ShortPatientModel patient = new ShortPatientModel(p);
		
		String name = request.getParameter("addName");
		if (p == null && name != null) {
			String gender = request.getParameter("addGender");
			String date = request.getParameter("addBirthdate");
			String age = request.getParameter("addAge");
			
			p = new Patient();
			PersonFormController.getMiniPerson(p, name, gender, date, age);
			
			patient = new ShortPatientModel(p);
		}
		
		if (patient.getAddress() == null) {
			PersonAddress pa = new PersonAddress();
			pa.setPreferred(true);
			patient.setAddress(pa);
		}
		
		return patient;
	}