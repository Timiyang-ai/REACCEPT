@Test
    public void testNegotiate() throws IOException, ServletException {
        final String securityPackage = "Negotiate";
        final SimpleFilterChain filterChain = new SimpleFilterChain();
        final SimpleHttpRequest request = new SimpleHttpRequest();

        final String clientToken = BaseEncoding.base64()
                .encode(WindowsAccountImpl.getCurrentUsername().getBytes(StandardCharsets.UTF_8));
        request.addHeader("Authorization", securityPackage + " " + clientToken);

        final SimpleHttpResponse response = new SimpleHttpResponse();
        this.filter.doFilter(request, response, filterChain);

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Assert.assertNotNull(auth);
        final Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        Assert.assertNotNull(authorities);
        Assert.assertEquals(3, authorities.size());
        final Iterator<? extends GrantedAuthority> authoritiesIterator = authorities.iterator();

        final List<String> list = new ArrayList<>();
        while (authoritiesIterator.hasNext()) {
            list.add(authoritiesIterator.next().getAuthority());
        }
        Collections.sort(list);
        Assert.assertEquals("ROLE_EVERYONE", list.get(0));
        Assert.assertEquals("ROLE_USER", list.get(1));
        Assert.assertEquals("ROLE_USERS", list.get(2));
        Assert.assertEquals(0, response.getHeaderNamesSize());
    }