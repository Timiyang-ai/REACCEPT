public Builder profileFile(Consumer<ProfileFile.Builder> profileFile) {
            return profileFile(ProfileFile.builder().apply(profileFile).build());
        }