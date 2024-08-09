    @Test(expected = IllegalArgumentException.class)
    public void getPermission_whenNonExistingService() {
        ActionConstants.getPermission("foo", "idon'texist");
    }