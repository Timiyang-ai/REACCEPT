public AuthenticationResult authenticate(WebRequest webRequest, WebAppSecurityConfig webAppSecConfig) {
        HttpServletRequest req = webRequest.getHttpServletRequest();
        HttpServletResponse res = webRequest.getHttpServletResponse();
        AuthenticationResult authResult = handleSSO(req, res);
        if (authResult != null && authResult.getStatus() == AuthResult.SUCCESS) {
            ssoCookieHelper.addJwtSsoCookiesToResponse(authResult.getSubject(), req, res);
        }
        return authResult;
    }