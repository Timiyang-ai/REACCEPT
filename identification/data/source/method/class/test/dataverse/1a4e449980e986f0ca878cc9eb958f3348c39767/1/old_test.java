    @Test
    void linkFor() {
        // given
        String redirectPage = "dataverse.xhtml"; // @see LoginPage.redirectPage
        String callbackURL = "oauth2/callback.xhtml";
        
        // when
        when(this.systemConfig.getOAuth2CallbackUrl()).thenReturn(callbackURL);
        
        String link = loginBackingBean.linkFor(testIdp.getId(), redirectPage);
        
        // then
        assertThat(link, notNullValue());
        assertThat(link, not(isEmptyString()));
        assertThat(link, StringContains.containsString(testIdp.getService(callbackURL).getAuthorizationUrl()));
    }