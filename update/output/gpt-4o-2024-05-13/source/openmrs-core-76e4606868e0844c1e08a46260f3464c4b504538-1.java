@Test
@Verifies(value = "should return default location for the implementation", method = "getDefaultLocation()")
public void getDefaultLocation_shouldReturnDefaultLocationForTheImplementation() throws Exception {
    // Set up the global property for the default location name
    String defaultLocationName = "Test Default Location";
    Context.getAdministrationService().saveGlobalProperty(new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, defaultLocationName));
    
    // Create and save a location with the name set in the global property
    Location location = new Location();
    location.setName(defaultLocationName);
    Context.getLocationService().saveLocation(location);
    
    // Verify that the default location is returned correctly
    Location defaultLocation = Context.getLocationService().getDefaultLocation();
    Assert.assertNotNull(defaultLocation);
    Assert.assertEquals(defaultLocationName, defaultLocation.getName());
    
    // Clean up by removing the global property and location
    Context.getAdministrationService().purgeGlobalProperty(new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME));
    Context.getLocationService().purgeLocation(location);
}