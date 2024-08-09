@Override
    public void updateTagsForCluster(
        @NotBlank(message = "No cluster id entered. Unable to update tags.") final String id,
        @NotEmpty(message = "No tags entered. Unable to update.") final Set<String> tags
    ) throws GenieException {
        this.findCluster(id).setTags(this.createAndGetTagEntities(tags));
        final ClusterEntity clusterEntity = this.findCluster(id);
        final Set<TagEntity> newTags = this.createAndGetTagEntities(tags);
        this.setFinalTags(newTags, clusterEntity.getUniqueId(), clusterEntity.getName());
        clusterEntity.setTags(newTags);
    }