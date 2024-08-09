@Override
    protected UserDetails processAutoLoginCookie(@Nonnull final String[] tokens, @Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response) {
        final List<GrantedAuthority> authorities = Arrays.asList(tokens).subList(1, tokens.length).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(tokens[0], "", authorities);
    }