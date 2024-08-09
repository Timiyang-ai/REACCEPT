@Override
    protected UserDetails processAutoLoginCookie(@Nonnull final String[] tokens, @Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response) {
        // KYLO-1504: At least once the tokens array size was observed to be less than 2 (should always be 2) so accounting for that.
        final Collection<? extends GrantedAuthority> authorities = tokens.length > 1 ? generateAuthorities(tokens[1]) : Collections.emptySet();
        return new User(tokens[0], "", authorities);
    }