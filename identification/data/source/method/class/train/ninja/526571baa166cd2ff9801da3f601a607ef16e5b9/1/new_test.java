    @Test
    public void rawPath() {
        String route = reverseRouter.with(TestController::user)
            .rawPathParam("email", "test@example.com")
            .pathParam("id", 1000000L)
            .build();
        
        assertThat(route, is("/user/test@example.com/1000000"));
    }