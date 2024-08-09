@Test
@Verifies(value = "should return default location for the implementation", method = "getDefaultLocation()")
public void getDefaultLocation_shouldReturnDefaultLocationForTheImplementation() throws Exception {
    String defaultLocationName = Context.getAdministrationService().getGlobalProperty(
        OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME);
    
    // If there's a specific global property set for the default location, verify it is returned
    if (StringUtils.hasText(defaultLocationName)) {
        Location defaultLocation = Context.getLocationService().getDefaultLocation();
        Assert.assertNotNull(defaultLocation);
        Assert.assertEquals(defaultLocationName, defaultLocation.getName());
    } else {
        // If no specific global property set, verify that "Unknown Location" or "Unknown" is returned as a fallback
        Location defaultLocation = Context.getLocationService().getDefaultLocation();
        Assert.assertNotNull(defaultLocation);
        boolean isUnknownOrFallback = "Unknown Location".equals(defaultLocation.getName()) || "Unknown".equals(defaultLocation.getName());
        Assert.assertTrue(isUnknownOrFallback);
    }
}