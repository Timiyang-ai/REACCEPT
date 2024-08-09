public String linkFor(String idpId, String redirectPage) {
        AbstractOAuth2AuthenticationProvider idp = authenticationSvc.getOAuth2Provider(idpId);
        OAuth20Service svc = idp.getService(systemConfig.getOAuth2CallbackUrl());
        String state = createState(idp, toOption(redirectPage));
        
        AuthorizationUrlBuilder aub = svc.createAuthorizationUrlBuilder()
                                         .state(state);
        
        // Do not include scope if empty string (necessary for GitHub)
        if (!idp.getSpacedScope().isEmpty()) { aub.scope(idp.getSpacedScope()); }
        
        return aub.build();
    }