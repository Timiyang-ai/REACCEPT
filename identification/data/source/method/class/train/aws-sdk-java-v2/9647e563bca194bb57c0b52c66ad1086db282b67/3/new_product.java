@Override
        public Builder profileFile(Consumer<ProfileFile.Builder> profileFile) {
            return profileFile(ProfileFile.builder().applyMutation(profileFile).build());
        }