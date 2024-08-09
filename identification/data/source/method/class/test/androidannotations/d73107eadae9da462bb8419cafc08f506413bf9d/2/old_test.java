	@Test
	public void authenticate() {
		RequestTestBuilder.build() //
				.requestHeader("SomeFancyHeader", "aFancyHeader") //
				.responseCookie("xt", "1234") //
				.responseCookie("sjsaid", "5678") //
				.asserts(new RequestTestBuilder.RequestTestBuilderExecutor() {
					@Override
					public void execute(MyService myService) {
						myService.authenticate();
					}
				});
	}