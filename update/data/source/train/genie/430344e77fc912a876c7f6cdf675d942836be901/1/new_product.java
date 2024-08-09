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
    public String getIndex(@NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response) {
        final Principal principal = request.getUserPrincipal();
        if (principal != null) {
            response.addCookie(new Cookie("genie.user", principal.getName()));
        } else {
            response.addCookie(new Cookie("genie.user", "user@genie"));
        }
        return "index";
    }