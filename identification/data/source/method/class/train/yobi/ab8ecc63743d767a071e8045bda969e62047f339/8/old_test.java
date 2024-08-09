    @Test
    public void newUser_AlreadyExistGroupName() {
        //Given
        Map<String, String> data = new HashMap<>();
        data.put("loginId", "labs");
        data.put("password", "somefakepassword");
        data.put("email", "labs@fake.com");
        data.put("name", "labs");

        //When
        Result result = callAction(
                controllers.routes.ref.UserApp.newUser(),
                fakeRequest().withFormUrlEncodedBody(data)
        );

        //Then
        assertThat(status(result)).describedAs("result status should '400 bad request'").isEqualTo(BAD_REQUEST);
    }