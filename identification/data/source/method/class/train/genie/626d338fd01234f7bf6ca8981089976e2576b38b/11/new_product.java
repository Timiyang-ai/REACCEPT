@Override
    public void removeAllTagsForCommand(
        @NotBlank(message = "No command id entered. Unable to remove tags.") final String id
    ) throws GenieException {
        this.findCommand(id).getTags().clear();
    }