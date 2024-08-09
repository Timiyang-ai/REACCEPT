	@Test
	public void savePatientProgram_shouldUpdatePatientProgram() {
		
		Date today = new Date();
		
		PatientProgram patientProgram = pws.getPatientProgram(1);
		Date dateCompleted = patientProgram.getDateCompleted();
		Date dateChanged = patientProgram.getDateChanged();
		User changedBy = patientProgram.getChangedBy();
		if (null != dateCompleted) {
			//System.out.println("Date Completed: " + dateCompleted);
		}
		if (null != dateChanged) {
			//System.out.println("Date Changed: " + dateChanged);
		}
		if (null != changedBy) {
			//System.out.println("Changed By: " + changedBy.toString());
		}
		
		patientProgram.setDateCompleted(today);
		patientProgram.setChangedBy(Context.getAuthenticatedUser());
		patientProgram.setDateChanged(today);
		pws.savePatientProgram(patientProgram);
		
		// Uncomment to commit to database
		// setComplete( );
		
		PatientProgram ptProg = pws.getPatientProgram(1);
		Date dateCompleted2 = patientProgram.getDateCompleted();
		Date dateChanged2 = patientProgram.getDateChanged();
		User changedBy2 = patientProgram.getChangedBy();

		if (null != dateCompleted2) {
			//System.out.println("Date Completed: " + dateCompleted2);
		}
		if (null != dateChanged2) {
			//System.out.println("Date Changed: " + dateChanged2);
		}
		if (null != changedBy2) {
			//System.out.println("Changed By: " + changedBy2.toString());
		}
		
		assertNotNull(ptProg.getDateCompleted());
		assertEquals(today, ptProg.getDateCompleted());
		
	}