@Test
public void checkPatientIdentifiers_shouldIgnoreVoidedPatientIdentifier() throws Exception {
    
    Patient patient = new Patient();
    PatientIdentifier patientIdentifier = new PatientIdentifier();
    patientIdentifier.setIdentifierType(Context.getPatientService().getAllPatientIdentifierTypes(false).get(0));
    patientIdentifier.setLocation(new Location(1));
    patientIdentifier.setVoided(true); // Using setVoided to match the new production code style
    patientIdentifier.setVoidedBy(Context.getAuthenticatedUser());
    patientIdentifier.setVoidReason("Testing whether voided identifiers are ignored");
    patient.addIdentifier(patientIdentifier);
    
    // Add a non-voided identifier so that the initial
    // "at least one nonvoided identifier" check passes
    PatientIdentifier nonVoidedIdentifier = new PatientIdentifier();
    nonVoidedIdentifier.setIdentifier("a non empty string");
    nonVoidedIdentifier.setIdentifierType(Context.getPatientService().getAllPatientIdentifierTypes(false).get(0));
    nonVoidedIdentifier.setLocation(new Location(1));
    nonVoidedIdentifier.setVoided(false); // Explicitly using setVoided(false)
    nonVoidedIdentifier.setVoidedBy(Context.getAuthenticatedUser());
    nonVoidedIdentifier.setVoidReason("Ensuring a non-voided identifier exists");
    patient.addIdentifier(nonVoidedIdentifier);
    
    // If the identifier is ignored, it won't throw an
    // InsufficientIdentifiersException as it should if only voided identifiers are present
    Context.getPatientService().checkPatientIdentifiers(patient);
    
}