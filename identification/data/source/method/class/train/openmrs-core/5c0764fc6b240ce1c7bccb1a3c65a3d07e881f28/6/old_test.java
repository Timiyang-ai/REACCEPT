	@Test(expected = APIException.class)
	public void getUserContext_shouldFailIfSessionHasntBeenOpened() {
		Context.closeSession();
		Context.getUserContext(); // trigger the api exception
	}