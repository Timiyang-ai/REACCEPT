public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (!supports(authentication.getClass())) {
            throw new IllegalArgumentException("Only SAMLAuthenticationToken is supported, " + authentication.getClass() + " was attempted");
        }

        SAMLAuthenticationToken token = (SAMLAuthenticationToken) authentication;
        SAMLMessageStorage store = token.getMessageStore();
        BasicSAMLMessageContext context = token.getCredentials();
        SAMLCredential credential;

        try {
            credential = consumer.processResponse(context, store);
        } catch (SAMLException e) {
            throw new AuthenticationServiceException("Error validating SAML message", e);
        } catch (ValidationException e) {
            log.debug("Error validating signature", e);
            throw new AuthenticationServiceException("Error validating SAML message signature", e);
        } catch (org.opensaml.xml.security.SecurityException e) {
            log.debug("Error validating signature", e);
            throw new AuthenticationServiceException("Error validating SAML message signature", e);
        }

        String name = credential.getNameID().getValue();
        Date expiration = getExpirationDate(credential);
        ExpiringUsernameAuthenticationToken result = new ExpiringUsernameAuthenticationToken(expiration, name, credential, new GrantedAuthority[0]);
        processUserDetails(result, credential);
        return result;
    }