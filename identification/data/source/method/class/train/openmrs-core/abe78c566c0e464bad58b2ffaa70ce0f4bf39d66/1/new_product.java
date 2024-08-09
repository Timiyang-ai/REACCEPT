public List<Patient> getPatients(String name, String identifier, List<PatientIdentifierType> identifierTypes,
	        boolean matchIdentifierExactly, Integer start, Integer length, boolean searchOnNamesOrIdentifiers)
	        throws DAOException;