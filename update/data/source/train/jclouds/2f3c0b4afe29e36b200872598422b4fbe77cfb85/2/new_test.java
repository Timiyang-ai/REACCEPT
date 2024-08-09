@Test(expectedExceptions = HttpResponseException.class)
   public void testCreateUser() throws Exception {
      adminConnection.getApi().deleteUser(PREFIX);
      adminConnection.getApi().createUser(new User(PREFIX));
      orgUser = adminConnection.getApi().getUser(PREFIX);
      assertNotNull(orgUser);
      assertEquals(orgUser.getUsername(), PREFIX);
      assertNotNull(orgUser.getPrivateKey());
   }