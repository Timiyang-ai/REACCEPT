public String linkFor(String idpId, String redirectPage) {
        AbstractOAuth2AuthenticationProvider idp = authenticationSvc.getOAuth2Provider(idpId);
        return idp.getService(createState(idp, toOption(redirectPage) ), getCallbackUrl()).getAuthorizationUrl();
    }