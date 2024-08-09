@Test
@Verifies(value = "should ignore voided patient identifier", method = "checkPatientIdentifiers(Patient)")
public void checkPatientIdentifiers_shouldIgnoreVoidedPatientIdentifier() throws Exception {
    
    Patient patient = new Patient();
    PatientIdentifier patientIdentifier = new PatientIdentifier();
    patientIdentifier.setIdentifierType(Context.getPatientService().getAllPatientIdentifierTypes(false).get(0));
    patientIdentifier.setLocation(new Location(1));
    patientIdentifier.setVoided(true);
    patientIdentifier.setVoidedBy(Context.getAuthenticatedUser());
    patientIdentifier.setVoidReason("Testing whether voided identifiers are ignored");
    patient.addIdentifier(patientIdentifier);
    
    // add a non-voided identifier so that the initial
    // "at least one nonvoided identifier" check passes
    patientIdentifier = new PatientIdentifier();
    patientIdentifier.setIdentifier("a non empty string");
    patientIdentifier.setIdentifierType(Context.getPatientService().getAllPatientIdentifierTypes(false).get(0));
    patientIdentifier.setLocation(new Location(1));
    patientIdentifier.setVoided(false);
    patientIdentifier.setVoidedBy(Context.getAuthenticatedUser());
    patientIdentifier.setVoidReason("Testing whether voided identifiers are ignored");
    patient.addIdentifier(patientIdentifier);
    
    // If the identifier is ignored, it won't throw a
    // InsufficientIdentifiersException as it should
    Context.getPatientService().checkPatientIdentifiers(patient);
    
}