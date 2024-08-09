@Test(enabled = false)
   public void testGetUser() throws Exception {
      User user = validatorConnection.getApi().getUser(orgname);
      assertNotNull(user);
   }