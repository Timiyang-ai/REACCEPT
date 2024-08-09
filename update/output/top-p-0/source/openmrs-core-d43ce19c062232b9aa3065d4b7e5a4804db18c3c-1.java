import org.junit.Assert;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.api.PatientIdentifierException;
import org.openmrs.test.jupiter.BaseModuleContextSensitiveTest;

public class OrderServiceTest extends BaseModuleContextSensitiveTest {

    @Test
    public void checkPatientIdentifiers_shouldIgnoreVoidedPatientIdentifier() throws Exception {
        
        Patient patient = new Patient();
        PatientIdentifier patientIdentifier = new PatientIdentifier();
        patientIdentifier.setIdentifierType(Context.getPatientService().getAllPatientIdentifierTypes(false).get(0));
        patientIdentifier.setLocation(new Location(1));
        patientIdentifier.setVoided(true); // Reflecting the change in method name for consistency
        patientIdentifier.setVoidedBy(Context.getAuthenticatedUser());
        patientIdentifier.setVoidReason("Testing whether voided identifiers are ignored");
        patient.addIdentifier(patientIdentifier);
        
        // add a non-voided identifier so that the initial
        // "at least one nonvoided identifier" check passes
        patientIdentifier = new PatientIdentifier();
        patientIdentifier.setIdentifier("a non empty string");
        patientIdentifier.setIdentifierType(Context.getPatientService().getAllPatientIdentifierTypes(false).get(0));
        patientIdentifier.setLocation(new Location(1));
        patientIdentifier.setVoided(false); // Reflecting the change in method name for consistency
        patientIdentifier.setVoidedBy(Context.getAuthenticatedUser());
        patientIdentifier.setVoidReason("Testing whether voided identifiers are ignored");
        patient.addIdentifier(patientIdentifier);
        
        // If the identifier is ignored, it won't throw a
        // BlankIdentifierException as it should
        Context.getPatientService().checkPatientIdentifiers(patient);
        
    }
}