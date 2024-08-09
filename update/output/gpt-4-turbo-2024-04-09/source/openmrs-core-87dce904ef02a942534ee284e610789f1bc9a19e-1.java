@Test
public void cloneForDiscontinuing_shouldSetThisCareSettingToNewOrder() throws IllegalAccessException, InstantiationException {
    Order anOrder = new Order();
    CareSetting careSetting = new CareSetting();
    anOrder.setCareSetting(careSetting);

    Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();

    assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());
    assertEquals(anOrder.getClass(), orderThatCanDiscontinueTheOrder.getClass(), "The class of the cloned order should be the same as the original");
}