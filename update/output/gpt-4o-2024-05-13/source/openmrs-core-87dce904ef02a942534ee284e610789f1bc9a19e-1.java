@Test
@Verifies(value = "set this care setting to new order", method = "cloneForDiscontinuing(Order)")
public void cloneForDiscontinuing_shouldSetThisCareSettingToNewOrder() throws IllegalAccessException, InstantiationException {
    Order anOrder = new Order();
    CareSetting careSetting = new CareSetting();
    anOrder.setCareSetting(careSetting);
    
    Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();
    
    assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
}