@Override
    protected UserDetails processAutoLoginCookie(@Nonnull final String[] tokens, @Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response) {
        final Collection<? extends GrantedAuthority> authorities = generateAuthorities(tokens[1]);
        return new User(tokens[0], "", authorities);
    }