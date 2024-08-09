@Override
    protected void onLoginSuccess(@Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response, @Nonnull final Authentication authentication) {
        final Stream<String> user = Stream.of(authentication.getPrincipal().toString());
        final Stream<String> token = Stream.of(generatePrincipalsToken(authentication.getAuthorities()));
        final String[] tokens = Stream.concat(user, token).toArray(String[]::new);

        setCookie(tokens, getTokenValiditySeconds(), request, response);
        
        // If there is a current Subject then add the cookie as a private credential.
        Subject subject = Subject.getSubject(AccessController.getContext());
        if (subject != null) {
            subject.getPrivateCredentials().add(getTokenCookie(tokens, request, response));
        }
    }