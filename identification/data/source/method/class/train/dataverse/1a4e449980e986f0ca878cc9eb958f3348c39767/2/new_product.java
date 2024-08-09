public String linkFor(String idpId, String redirectPage) {
        AbstractOAuth2AuthenticationProvider idp = authenticationSvc.getOAuth2Provider(idpId);
        String state = createState(idp, toOption(redirectPage));
        return idp.buildAuthzUrl(state, systemConfig.getOAuth2CallbackUrl());
    }