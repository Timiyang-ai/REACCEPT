@RequiresAuthentication
	@Post("http://company.com/oauth/token")
    @SetsCookie({"xt", "sjsaid"})
	void authenticate();