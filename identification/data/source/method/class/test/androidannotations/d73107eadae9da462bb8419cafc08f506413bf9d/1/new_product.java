@RequiresAuthentication
	@RequiresHeader("SomeFancyHeader")
	@Post("http://company.com/oauth/token")
    @SetsCookie({"xt", "sjsaid"})
	void authenticate();