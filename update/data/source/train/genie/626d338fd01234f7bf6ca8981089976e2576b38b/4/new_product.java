@Override
    public void updateTagsForCommand(
        @NotBlank(message = "No command id entered. Unable to update tags.") final String id,
        @NotEmpty(message = "No tags entered. Unable to update.") final Set<String> tags
    ) throws GenieException {
        this.findCommand(id).setTags(this.createAndGetTagEntities(tags));
    }