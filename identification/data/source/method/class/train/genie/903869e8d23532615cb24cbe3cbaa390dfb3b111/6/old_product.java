@Override
    public void updateTagsForApplication(
        @NotBlank(message = "No application id entered. Unable to update tags.") final String id,
        @NotNull(message = "No tags entered unable to update tags.") final Set<String> tags
    ) throws GenieException {
        final ApplicationEntity applicationEntity = this.findApplication(id);
        final Set<TagEntity> newTags = this.createAndGetTagEntities(tags);
        this.setFinalTags(newTags, applicationEntity.getUniqueId(), applicationEntity.getName());
        applicationEntity.setTags(newTags);
    }