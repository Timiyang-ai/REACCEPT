public final boolean supports(final Credential credential) {
        return credential != null
            && (this.classToSupport.equals(credential.getClass()) || (this.classToSupport
                .isAssignableFrom(credential.getClass()))
                && this.supportSubClasses);
    }