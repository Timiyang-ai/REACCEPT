public Optional<Profile> profile(String profileName) {
        return Optional.ofNullable(profiles.get(profileName));
    }