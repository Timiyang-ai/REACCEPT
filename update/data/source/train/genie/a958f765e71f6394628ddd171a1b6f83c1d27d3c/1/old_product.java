@GetMapping(
        value = {
            "/",
            "/applications/**",
            "/clusters/**",
            "/commands/**",
            "/jobs/**",
            "/output/**"
        }
    )
    public String getIndex(@NotNull final HttpServletResponse response, @Nullable final Authentication authentication) {
        if (authentication != null) {
            response.addCookie(new Cookie("genie.user", authentication.getName()));
        } else {
            response.addCookie(new Cookie("genie.user", "user@genie"));
        }
        return "index.html";
    }