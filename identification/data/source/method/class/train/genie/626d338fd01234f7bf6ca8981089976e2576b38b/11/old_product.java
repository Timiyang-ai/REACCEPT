@Override
    public void removeAllTagsForCommand(
        @NotBlank(message = "No command id entered. Unable to remove tags.") final String id
    ) throws GenieException {
        final Set<TagEntity> tags = this.findCommand(id).getTags();
        // Remove all the tags except the ones that start with "genie."
        tags.removeAll(
            tags
                .stream()
                .filter(
                    tagEntity -> !tagEntity.getTag().startsWith(JpaBaseService.GENIE_TAG_NAMESPACE)
                )
                .collect(Collectors.toSet())
        );
    }