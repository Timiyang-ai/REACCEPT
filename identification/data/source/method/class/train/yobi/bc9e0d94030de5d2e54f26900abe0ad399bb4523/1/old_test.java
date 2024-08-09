    @Test
    public void login_notComfirmedUser() {
        //Given
        User user = new User(-31l);
        user.loginId = "fakeUser";
        user.email = "fakeuser@fake.com";
        user.name = "racoon";
        user.password = "somefakepassword";
        user.createdDate = JodaDateUtil.now();
        user.state = UserState.LOCKED;
        user.save();

        Map<String, String> data = new HashMap<>();
        data.put("loginIdOrEmail", user.loginId);
        data.put("password", user.password);

        //When
        Result result = callAction(
                controllers.routes.ref.UserApp.login(),
                fakeRequest().withFormUrlEncodedBody(data)
        );

        //Then
        assertThat(status(result)).describedAs("result status should '303 see other'").isEqualTo(303);
    }