@Test(expectedExceptions = HttpResponseException.class)
   public void testGetUser() throws Exception {
      adminConnection.getApi().getUser(user);
   }