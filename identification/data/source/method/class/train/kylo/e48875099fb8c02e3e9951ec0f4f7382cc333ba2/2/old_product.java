@Override
    protected void onLoginSuccess(@Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response, @Nonnull final Authentication authentication) {
        final Stream<String> user = Stream.of(authentication.getPrincipal().toString());
        final Stream<String> groups = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
        final String[] tokens = Stream.concat(user, groups).toArray(String[]::new);

        setCookie(tokens, getTokenValiditySeconds(), request, response);
    }