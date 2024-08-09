@Test(dependsOnMethods = "addUser")
    public void updateUser() throws Exception {
        final UserMgmtService userMgmtService = getUserMgmtService();

        JSONObject requestJSONObject = new JSONObject();

        requestJSONObject.put(User.USER_NAME, "user2 name");
        requestJSONObject.put(User.USER_EMAIL, "test2@gmail.com");
        requestJSONObject.put(User.USER_PASSWORD, "pass2");
        requestJSONObject.put(User.USER_ROLE, Role.ADMIN_ROLE);

        final String id = userMgmtService.addUser(requestJSONObject);
        Assert.assertNotNull(id);

        requestJSONObject.put(Keys.OBJECT_ID, id);
        requestJSONObject.put(User.USER_NAME, "user2 new name");

        userMgmtService.updateUser(requestJSONObject);

        Assert.assertEquals(getUserQueryService().getUser(id).getJSONObject(
                User.USER).getString(User.USER_NAME), "user2 new name");

        // Do not update password
        requestJSONObject.put(Keys.OBJECT_ID, id);
        requestJSONObject.put(User.USER_NAME, "user2 name");
        requestJSONObject.put(User.USER_EMAIL, "test2@gmail.com");
        requestJSONObject.put(User.USER_PASSWORD, "pass2");

        userMgmtService.updateUser(requestJSONObject);

        Assert.assertEquals(getUserQueryService().getUser(id).getJSONObject(
                User.USER).getString(User.USER_PASSWORD), MD5.hash("pass2"));
    }