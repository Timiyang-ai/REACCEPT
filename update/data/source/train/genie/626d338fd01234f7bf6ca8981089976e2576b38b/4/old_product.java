@Override
    public void updateTagsForCommand(
        @NotBlank(message = "No command id entered. Unable to update tags.") final String id,
        @NotEmpty(message = "No tags entered. Unable to update.") final Set<String> tags
    ) throws GenieException {
        final CommandEntity commandEntity = this.findCommand(id);
        final Set<TagEntity> newTags = this.createAndGetTagEntities(tags);
        this.setFinalTags(newTags, commandEntity.getUniqueId(), commandEntity.getName());
        commandEntity.setTags(newTags);
    }