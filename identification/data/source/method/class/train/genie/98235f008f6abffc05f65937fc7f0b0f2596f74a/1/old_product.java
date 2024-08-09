public static Specification<ClusterEntity> findByClusterAndCommandCriteria(
            final ClusterCriteria clusterCriteria,
            final Set<String> commandCriteria) {
        return (final Root<ClusterEntity> root, final CriteriaQuery<?> cq, final CriteriaBuilder cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            final Join<ClusterEntity, CommandEntity> commands = root.join(ClusterEntity_.commands);

            cq.distinct(true);

            predicates.add(cb.equal(commands.get(CommandEntity_.status), CommandStatus.ACTIVE));
            predicates.add(cb.equal(root.get(ClusterEntity_.status), ClusterStatus.UP));

            if (commandCriteria != null && !commandCriteria.isEmpty()) {
                predicates.add(
                        cb.like(
                                commands.get(CommandEntity_.sortedTags),
                                JpaSpecificationUtils.getTagLikeString(commandCriteria)
                        )
                );
            }

            if (clusterCriteria != null && clusterCriteria.getTags() != null && !clusterCriteria.getTags().isEmpty()) {
                predicates.add(
                        cb.like(
                                root.get(ClusterEntity_.sortedTags),
                                JpaSpecificationUtils.getTagLikeString(clusterCriteria.getTags())
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }