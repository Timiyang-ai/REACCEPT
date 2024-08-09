import org.junit.Assert;
import org.junit.Test;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.hibernate.HibernatePersonDAO;
import org.openmrs.util.PersonAttributeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ConceptDAOTest {

    private static final Logger log = LoggerFactory.getLogger(ConceptDAOTest.class);
    private HibernatePersonDAO hibernatePersonDAO;
    private PersonAttributeHelper personAttributeHelper;

    @Test
    public void getPeople_shouldGetOnePersonByRandomCaseAttribute() throws Exception {
        Assert.assertTrue(personAttributeHelper.personAttributeExists("Story teller"));

        // Assuming MatchMode is an enum or class that needs to be set
        MatchMode matchMode = MatchMode.ANYWHERE; // or whatever the appropriate match mode is
        List<Person> people = hibernatePersonDAO.getPeople("sToRy TeLlEr", false, matchMode);
        logPeople(people);

        Assert.assertEquals(1, people.size());
        Assert.assertEquals("Bilbo Odilon", people.get(0).getGivenName());
    }

    private void logPeople(List<Person> people) {
        for (Person person : people) {
            log.info("Found person: " + person.getGivenName() + " " + person.getFamilyName());
        }
    }
}