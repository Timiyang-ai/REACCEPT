public String linkFor(String idpId, String redirectPage) {
        AbstractOAuth2AuthenticationProvider idp = authenticationSvc.getOAuth2Provider(idpId);
        OAuth20Service svc = idp.getService(systemConfig.getOAuth2CallbackUrl());
        String state = createState(idp, toOption(redirectPage));
        
        return svc.createAuthorizationUrlBuilder()
                  .state(state)
                  .scope(idp.getScope())
                  .build();
    }