import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

    @Test
    public void cloneForDiscontinuing_shouldSetThisCareSettingToNewOrder() throws IllegalAccessException, InstantiationException {
        Order anOrder = new Order();
        CareSetting careSetting = new CareSetting();
        anOrder.setCareSetting(careSetting);
        
        // Since the production method now throws exceptions, we need to handle them
        Order orderThatCanDiscontinueTheOrder = null;
        
        try {
            orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();
        } catch (IllegalAccessException | InstantiationException e) {
            fail("The cloning process should not throw an exception");
        }

        // Verify the cloned object shares the same class type with the original
        assertEquals("The cloned object should be of the same type as the original", anOrder.getClass(), orderThatCanDiscontinueTheOrder.getClass());
        
        assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
    }
}
//