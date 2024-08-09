@RequiresHeader("SomeFancyHeader")
	@Post("/login")
    @SetsCookie({"xt", "sjsaid"})
	void authenticate();