@Test
@Verifies(value = "should return default location based on global property name, fallback to 'Unknown Location' or 'Unknown'", method = "getDefaultLocation()")
public void getDefaultLocation_shouldReturnDefaultLocationBasedOnGlobalPropertyNameFallbackToUnknownLocationOrUnknown() throws Exception {
    // Set up global property for default location name
    AdministrationService as = Context.getAdministrationService();
    as.setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Test Location");
    
    // Assuming getLocation(String name) method correctly fetches location by name
    LocationService ls = Context.getLocationService();
    Location testLocation = new Location();
    testLocation.setName("Test Location");
    ls.saveLocation(testLocation);
    
    // Verify getDefaultLocation returns the location set by global property
    Assert.assertEquals("Test Location", ls.getDefaultLocation().getName());
    
    // Change global property to a non-existing location and verify fallback to 'Unknown Location'
    as.setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Non Existing Location");
    Location unknownLocation = new Location();
    unknownLocation.setName("Unknown Location");
    ls.saveLocation(unknownLocation);
    
    Assert.assertEquals("Unknown Location", ls.getDefaultLocation().getName());
    
    // Verify fallback to 'Unknown' if 'Unknown Location' also doesn't exist
    as.setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Another Non Existing Location");
    Location unknown = new Location();
    unknown.setName("Unknown");
    ls.saveLocation(unknown);
    
    Assert.assertEquals("Unknown", ls.getDefaultLocation().getName());
    
    // Clean up
    as.setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "");
}