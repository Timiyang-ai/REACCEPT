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
        } catch (DecryptionException e) {
            log.debug("Error decrypting SAML message", e);
            throw new AuthenticationServiceException("Error decrypting SAML message", e);
        }

        Object userDetails = getUserDetails(credential);
        Object principal = getPrincipal(credential, userDetails);
        Collection<GrantedAuthority> entitlements = getEntitlements(credential, userDetails);

        Date expiration = getExpirationDate(credential);
        ExpiringUsernameAuthenticationToken result = new ExpiringUsernameAuthenticationToken(expiration, principal, credential, entitlements);
        result.setDetails(userDetails);
        return result;
    }