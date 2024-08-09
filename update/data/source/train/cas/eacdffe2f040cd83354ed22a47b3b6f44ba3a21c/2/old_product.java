public final boolean supports(final Credentials credentials) {
        return credentials != null
            && (this.classToSupport.equals(credentials.getClass()) || (this.classToSupport
                .isAssignableFrom(credentials.getClass()))
                && this.supportSubClasses);
    }