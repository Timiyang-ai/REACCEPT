import org.junit.Assert;
import org.junit.Test;
import org.openmrs.PersonName;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;

public class PersonServiceTest {

    @Test
    public void parsePersonName_shouldCorrectlyParseFourPartNames() throws Exception {
        // Setup
        PersonService personService = Context.getPersonService();

        // Execute
        PersonName personName = personService.parsePersonName("John Adam Smith Doe");

        // Verify
        Assert.assertEquals("John", personName.getGivenName());
        Assert.assertEquals("Adam", personName.getMiddleName());
        Assert.assertEquals("Smith", personName.getFamilyName());
        Assert.assertEquals("Doe", personName.getFamilyName2());

        // Additional test to verify handling of names with commas and no spaces
        personName = personService.parsePersonName("Doe,John Adam Smith");

        // Verify
        Assert.assertEquals("John", personName.getGivenName());
        Assert.assertEquals("Adam", personName.getMiddleName());
        Assert.assertEquals("Smith", personName.getFamilyName());
        Assert.assertEquals("Doe", personName.getFamilyName2());
    }
}