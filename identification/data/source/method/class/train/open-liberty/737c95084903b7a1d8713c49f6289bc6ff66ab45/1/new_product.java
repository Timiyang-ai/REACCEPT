@Override
    public String getChangedProperties(WebAppSecurityConfig original) {
        // Bail out if it is the same object, or if this isn't of the right type.
        if (this == original) {
            return "";
        }
        if (!(original instanceof WebAppSecurityConfigImpl)) {
            return "";
        }

        StringBuffer buf = new StringBuffer();
        WebAppSecurityConfigImpl orig = (WebAppSecurityConfigImpl) original;
        appendToBufferIfDifferent(buf, CFG_KEY_FAIL_OVER_TO_BASICAUTH,
                                  this.allowFailOverToBasicAuth, orig.allowFailOverToBasicAuth);
        appendToBufferIfDifferent(buf, "allowLogoutPageRedirectToAnyHost",
                                  this.allowLogoutPageRedirectToAnyHost, orig.allowLogoutPageRedirectToAnyHost);
        appendToBufferIfDifferent(buf, "displayAuthenticationRealm",
                                  this.displayAuthenticationRealm, orig.displayAuthenticationRealm);
        appendToBufferIfDifferent(buf, "httpOnlyCookies",
                                  this.httpOnlyCookies, orig.httpOnlyCookies);
        appendToBufferIfDifferent(buf, "jaspicSessionCookieName",
                                  this.jaspicSessionCookieName, orig.jaspicSessionCookieName);
        appendToBufferIfDifferent(buf, "jaspicSessionForMechanismsEnabled",
                                  this.jaspicSessionForMechanismsEnabled, orig.jaspicSessionForMechanismsEnabled);
        appendToBufferIfDifferent(buf, "logoutOnHttpSessionExpire",
                                  this.logoutOnHttpSessionExpire, orig.logoutOnHttpSessionExpire);
        appendToBufferIfDifferent(buf, "logoutPageRedirectDomainNames",
                                  this.logoutPageRedirectDomainNames, orig.logoutPageRedirectDomainNames);
        appendToBufferIfDifferent(buf, "preserveFullyQualifiedReferrerUrl",
                                  this.preserveFullyQualifiedReferrerUrl, orig.preserveFullyQualifiedReferrerUrl);
        appendToBufferIfDifferent(buf, "postParamCookieSize",
                                  this.postParamCookieSize, orig.postParamCookieSize);
        appendToBufferIfDifferent(buf, "postParamSaveMethod",
                                  this.postParamSaveMethod, orig.postParamSaveMethod);
        appendToBufferIfDifferent(buf, "singleSignonEnabled",
                                  this.singleSignonEnabled, orig.singleSignonEnabled);
        appendToBufferIfDifferent(buf, "ssoCookieName",
                                  this.ssoCookieName, orig.ssoCookieName);
        appendToBufferIfDifferent(buf, "autoGenSsoCookieName",
                                  this.autoGenSsoCookieName, orig.autoGenSsoCookieName);
        appendToBufferIfDifferent(buf, "ssoDomainNames",
                                  this.ssoDomainNames, orig.ssoDomainNames);
        appendToBufferIfDifferent(buf, "ssoRequiresSSL",
                                  this.ssoRequiresSSL, orig.ssoRequiresSSL);
        appendToBufferIfDifferent(buf, "ssoUseDomainFromURL",
                                  this.ssoUseDomainFromURL, orig.ssoUseDomainFromURL);
        appendToBufferIfDifferent(buf, "useAuthenticationDataForUnprotectedResource",
                                  this.useAuthenticationDataForUnprotectedResource, orig.useAuthenticationDataForUnprotectedResource);
        appendToBufferIfDifferent(buf, "webAlwaysLogin",
                                  this.webAlwaysLogin, orig.webAlwaysLogin);
        appendToBufferIfDifferent(buf, CFG_KEY_LOGIN_FORM_URL,
                                  this.loginFormURL, orig.loginFormURL);
        appendToBufferIfDifferent(buf, CFG_KEY_LOGIN_ERROR_URL,
                                  this.loginErrorURL, orig.loginErrorURL);
        appendToBufferIfDifferent(buf, CFG_KEY_ALLOW_FAIL_OVER_TO_AUTH_METHOD,
                                  this.allowFailOverToAuthMethod, orig.allowFailOverToAuthMethod);
        appendToBufferIfDifferent(buf, "includePathInWASReqURL",
                                  this.includePathInWASReqURL, orig.includePathInWASReqURL);
        appendToBufferIfDifferent(buf, "trackLoggedOutSSOCookies",
                                  this.trackLoggedOutSSOCookies, orig.trackLoggedOutSSOCookies);
        appendToBufferIfDifferent(buf, "useOnlyCustomCookieName",
                                  this.useOnlyCustomCookieName, orig.useOnlyCustomCookieName);
        appendToBufferIfDifferent(buf, "wasReqURLRedirectDomainNames",
                                  this.wasReqURLRedirectDomainNames, orig.wasReqURLRedirectDomainNames);
        appendToBufferIfDifferent(buf, CFG_KEY_OVERRIDE_HAM,
                                  this.overrideHttpAuthenticationMechanism, orig.overrideHttpAuthenticationMechanism);
        appendToBufferIfDifferent(buf, CFG_KEY_LOGIN_FORM_CONTEXT_ROOT,
                                  this.loginFormContextRoot, orig.loginFormContextRoot);
        appendToBufferIfDifferent(buf, CFG_KEY_BASIC_AUTH_REALM_NAME,
                                  this.basicAuthRealmName, orig.basicAuthRealmName);
        return buf.toString();
    }