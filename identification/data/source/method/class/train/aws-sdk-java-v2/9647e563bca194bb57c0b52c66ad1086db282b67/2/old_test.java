    private ProfileFile profileFile(String string) {
        return ProfileFile.builder().content(new StringInputStream(string)).type(ProfileFile.Type.CONFIGURATION).build();
    }