public static Specification<ClusterEntity> findByClusterAndCommandCriteria(
            final ClusterCriteria clusterCriteria,
            final Set<String> commandCriteria) {
        return (final Root<ClusterEntity> root, final CriteriaQuery<?> cq, final CriteriaBuilder cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            final Join<ClusterEntity, CommandEntity> commands = root.join(ClusterEntity_.commands);

            cq.distinct(true);

            predicates.add(cb.equal(commands.get(CommandEntity_.status), CommandStatus.ACTIVE));
            predicates.add(cb.equal(root.get(ClusterEntity_.status), ClusterStatus.UP));

            if (commandCriteria != null) {
                final StringBuilder builder = new StringBuilder();
                builder.append("%");
                commandCriteria.stream()
                        .filter(StringUtils::isNotBlank)
                        .map(String::toLowerCase)
                        .sorted()
                        .forEach(tag -> builder.append(tag).append("%"));
                predicates.add(cb.like(commands.get(CommandEntity_.sortedTags), builder.toString()));
            }

            if (clusterCriteria != null) {
                final StringBuilder builder = new StringBuilder();
                builder.append("%");
                clusterCriteria.getTags().stream()
                        .filter(StringUtils::isNotBlank)
                        .map(String::toLowerCase)
                        .sorted()
                        .forEach(tag -> builder.append(tag).append("%"));
                predicates.add(cb.like(root.get(ClusterEntity_.sortedTags), builder.toString()));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }