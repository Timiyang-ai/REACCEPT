@Override
    protected void onLoginSuccess(@Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response, @Nonnull final Authentication authentication) {
        final String user = authentication.getPrincipal().toString();
        final String principals = generatePrincipalsToken(authentication.getAuthorities());
        final String[] tokens =  Arrays.asList(user, principals).stream().toArray(String[]::new);

        setCookie(tokens, getTokenValiditySeconds(), request, response);
    }