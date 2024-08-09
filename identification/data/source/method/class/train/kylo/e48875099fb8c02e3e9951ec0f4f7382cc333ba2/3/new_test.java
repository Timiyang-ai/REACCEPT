    @Test
    public void processAutoLoginCookie() throws Exception {
        final UserDetails user = service.processAutoLoginCookie(new String[]{"dladmin", groupPrincipalsJson("admin")}, Mockito.mock(HttpServletRequest.class), Mockito.mock(HttpServletResponse.class));
        Assert.assertEquals("dladmin", user.getUsername());
        
        Principal group = user.getAuthorities().stream()
                        .findAny()
                        .map(JaasGrantedAuthority.class::cast)
                        .map(ja -> ja.getPrincipal())
                        .orElseThrow(() -> new AssertionError("No group principal found"));
        Assert.assertEquals(new GroupPrincipal("admin"), group);
    }