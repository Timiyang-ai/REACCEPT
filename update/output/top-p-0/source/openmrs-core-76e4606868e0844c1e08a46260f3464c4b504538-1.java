@Test
@Verifies(value = "should return default location based on global property or fallback to 'Unknown Location' or 'Unknown'", method = "getDefaultLocation()")
public void getDefaultLocation_shouldReturnDefaultLocationBasedOnGlobalPropertyOrFallback() throws Exception {
    AdministrationService adminService = Context.getAdministrationService();
    LocationService locationService = Context.getLocationService();
    
    // Set a global property for the default location name
    final String defaultLocationName = "Test Default Location";
    adminService.saveGlobalProperty(new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, defaultLocationName));
    
    // Ensure a location with the name set in the global property exists
    Location defaultLocation = new Location();
    defaultLocation.setName(defaultLocationName);
    locationService.saveLocation(defaultLocation);
    
    // Test that the default location is returned based on the global property
    Location retrievedLocation = locationService.getDefaultLocation();
    Assert.assertNotNull("The default location should not be null", retrievedLocation);
    Assert.assertEquals("The retrieved location should match the global property value", defaultLocationName, retrievedLocation.getName());
    
    // Change the global property to a non-existent location name to test fallback
    adminService.saveGlobalProperty(new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Non Existent Location"));
    
    // Test fallback to "Unknown Location" if the global property does not match
    Location unknownLocation = new Location();
    unknownLocation.setName("Unknown Location");
    locationService.saveLocation(unknownLocation);
    
    retrievedLocation = locationService.getDefaultLocation();
    Assert.assertNotNull("The default location should fallback to 'Unknown Location' if the global property does not match", retrievedLocation);
    Assert.assertEquals("The retrieved location should be 'Unknown Location' as a fallback", "Unknown Location", retrievedLocation.getName());
    
    // Remove "Unknown Location" to test final fallback to "Unknown"
    locationService.purgeLocation(unknownLocation);
    Location unknownFallbackLocation = new Location();
    unknownFallbackLocation.setName("Unknown");
    locationService.saveLocation(unknownFallbackLocation);
    
    retrievedLocation = locationService.getDefaultLocation();
    Assert.assertNotNull("The default location should fallback to 'Unknown' if 'Unknown Location' does not exist", retrievedLocation);
    Assert.assertEquals("The retrieved location should be 'Unknown' as the final fallback", "Unknown", retrievedLocation.getName());
}