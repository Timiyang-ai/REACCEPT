import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.OrderFrequency;
import static org.junit.Assert.assertNotNull;

public class OrderFrequencyServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void saveOrderFrequency_shouldNotAllowEditingAnExistingOrderFrequencyThatIsInUse() throws Exception {
        // Fetch an existing order frequency that is known to be in use
        OrderFrequency orderFrequency = Context.getOrderService().getOrderFrequency(1);
        assertNotNull("The order frequency should not be null", orderFrequency);
        
        // Attempt to modify the order frequency
        orderFrequency.setFrequencyPerDay(4d);
        
        // Set up expectations for the exception
        expectedException.expect(APIException.class);
        expectedException.expectMessage("Order.frequency.cannot.edit");
        
        // Attempt to save the modified order frequency, which should fail
        Context.getOrderService().saveOrderFrequency(orderFrequency);
    }
}