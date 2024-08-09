@Test
@Verifies(value = "should return default location for the implementation", method = "getDefaultLocation()")
public void getDefaultLocation_shouldReturnDefaultLocationForTheImplementation() throws Exception {
    // Set the global property for the default location name to ensure the test environment is correctly setup
    Context.getAdministrationService().setGlobalProperty(
        OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Test Default Location");

    // Ensure that a location named "Test Default Location" exists in the database for this test
    Location testDefaultLocation = new Location();
    testDefaultLocation.setName("Test Default Location");
    Context.getLocationService().saveLocation(testDefaultLocation);

    // Assert that the default location is correctly returned based on the global property set above
    Location defaultLocation = Context.getLocationService().getDefaultLocation();
    Assert.assertNotNull(defaultLocation);
    Assert.assertEquals("Test Default Location", defaultLocation.getName());

    // Cleanup by removing the test location and resetting the global property if necessary
    Context.getLocationService().purgeLocation(testDefaultLocation);
    Context.getAdministrationService().setGlobalProperty(
        OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "");
}