@Test
public void cloneForDiscontinuing_shouldCorrectlyCloneOrderWithNewInstance() throws IllegalAccessException, InstantiationException {
    Order anOrder = new Order();
    CareSetting careSetting = new CareSetting();
    Concept concept = new Concept();
    anOrder.setCareSetting(careSetting);
    anOrder.setConcept(concept);
    anOrder.setAction(Order.Action.NEW);

    Order orderThatCanDiscontinueTheOrder = anOrder.cloneForDiscontinuing();

    // Verify that the cloned order is of the same class as the original order
    assertEquals(anOrder.getClass(), orderThatCanDiscontinueTheOrder.getClass());

    // Verify that the care setting is copied to the new order
    assertEquals(anOrder.getCareSetting(), orderThatCanDiscontinueTheOrder.getCareSetting());

    // Verify that the concept is copied to the new order
    assertEquals(anOrder.getConcept(), orderThatCanDiscontinueTheOrder.getConcept());

    // Verify that the action of the new order is set to DISCONTINUE
    assertEquals(Order.Action.DISCONTINUE, orderThatCanDiscontinueTheOrder.getAction());

    // Since cloneForDiscontinuing now uses getClass().newInstance(), ensure that the new order is indeed a new instance
    assertNotSame("The cloned order should be a new instance", anOrder, orderThatCanDiscontinueTheOrder);
}