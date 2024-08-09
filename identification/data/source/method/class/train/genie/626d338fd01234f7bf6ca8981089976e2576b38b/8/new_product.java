@Override
    public void removeAllTagsForCluster(
        @NotBlank(message = "No cluster id entered. Unable to remove tags.") final String id
    ) throws GenieException {
        this.findCluster(id).getTags().clear();
    }