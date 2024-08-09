	@Test
	public void logout_shouldNotFailIfSessionHasntBeenOpenedYet() {
		Context.closeSession();
		Context.logout();
	}