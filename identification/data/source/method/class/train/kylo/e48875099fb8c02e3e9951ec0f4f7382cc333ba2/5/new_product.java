@Override
    protected void onLoginSuccess(@Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response, @Nonnull final Authentication authentication) {
        final Stream<String> user = Stream.of(authentication.getPrincipal().toString());
        final Stream<String> token = Stream.of(generatePrincipalsToken(authentication.getAuthorities()));
        final String[] tokens = Stream.concat(user, token).toArray(String[]::new);

        setCookie(tokens, getTokenValiditySeconds(), request, response);
    }