    @Test
    public void path() {
        String route = reverseRouter.with(TestController::user)
            .pathParam("email", "test@example.com")
            .pathParam("id", 1000000L)
            .build();
        
        assertThat(route, is("/user/test%40example.com/1000000"));
    }