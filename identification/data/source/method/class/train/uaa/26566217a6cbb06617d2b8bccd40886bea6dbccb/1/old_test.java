    @Test
    public void findMatchingRedirectUri_usesAntPathMatching() {
        //matches pattern
        String matchingRedirectUri1 = UaaUrlUtils.findMatchingRedirectUri(Collections.singleton("http://matching.redirect/*"), "http://matching.redirect/", null);
        assertThat(matchingRedirectUri1, equalTo("http://matching.redirect/"));

        //matches pattern

        String matchingRedirectUri2 = UaaUrlUtils.findMatchingRedirectUri(Collections.singleton("http://matching.redirect/*"), "http://matching.redirect/anything-but-forward-slash", null);
        assertThat(matchingRedirectUri2, equalTo("http://matching.redirect/anything-but-forward-slash"));

        //does not match pattern, but no fallback
        matchingRedirectUri2 = UaaUrlUtils.findMatchingRedirectUri(Collections.singleton("http://matching.redirect/*"), "http://does.not.match/redirect", null);
        assertThat(matchingRedirectUri2, equalTo("http://does.not.match/redirect"));

        //does not match pattern, but fallback provided
        matchingRedirectUri2 = UaaUrlUtils.findMatchingRedirectUri(Collections.singleton("http://matching.redirect/*"), "http://does.not.match/redirect", "http://fallback.url/redirect");
        assertThat(matchingRedirectUri2, equalTo("http://fallback.url/redirect"));

        String pattern2 = "http://matching.redirect/**";
        String redirect3 = "http://matching.redirect/whatever/you/want";
        String matchingRedirectUri3 = UaaUrlUtils.findMatchingRedirectUri(Collections.singleton(pattern2), redirect3, null);
        assertThat(matchingRedirectUri3, equalTo(redirect3));

        String pattern3 = "http://matching.redirect/?";
        String redirect4 = "http://matching.redirect/t";
        String matchingRedirectUri4 = UaaUrlUtils.findMatchingRedirectUri(Collections.singleton(pattern3), redirect4, null);
        assertThat(matchingRedirectUri4, equalTo(redirect4));

        String redirect5 = "http://non-matching.redirect/two";
        String fallback = "http://fallback.to/this";
        String matchingRedirectUri5 = UaaUrlUtils.findMatchingRedirectUri(Collections.singleton(pattern3), redirect5, fallback);
        assertThat(matchingRedirectUri5, equalTo(fallback));
    }