@Override
	public PatientProgram savePatientProgram(PatientProgram patientProgram) throws DAOException {
                CustomDatatypeUtil.saveAttributesIfNecessary(patientProgram);

                if (patientProgram.getPatientProgramId() == null) {
			sessionFactory.getCurrentSession().save(patientProgram);
		} else {
			sessionFactory.getCurrentSession().merge(patientProgram);
		}
                
		return patientProgram;
	}