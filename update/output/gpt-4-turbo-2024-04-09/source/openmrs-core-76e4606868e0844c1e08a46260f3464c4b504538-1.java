@Test
@Verifies(value = "should return default location for the implementation", method = "getDefaultLocation()")
public void getDefaultLocation_shouldReturnDefaultLocationForTheImplementation() throws Exception {
    // Set the global property to a specific location name for testing
    Context.getAdministrationService().setGlobalProperty(
        OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Test Location");

    // Create and save the "Test Location" to ensure it exists in the database
    Location testLocation = new Location();
    testLocation.setName("Test Location");
    Context.getLocationService().saveLocation(testLocation);

    // Test the method to see if it retrieves the correct default location
    Location defaultLocation = Context.getLocationService().getDefaultLocation();
    Assert.assertNotNull(defaultLocation);
    Assert.assertEquals("Test Location", defaultLocation.getName());

    // Clean up by removing the test location and resetting the global property
    Context.getLocationService().purgeLocation(testLocation);
    Context.getAdministrationService().setGlobalProperty(
        OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "");
}