@Test
    public void testCreateUser() throws Exception {
        UserDto user = createUser(KaaAuthorityDto.TENANT_DEVELOPER);
        Assert.assertFalse(strIsEmpty(user.getId()));
    }