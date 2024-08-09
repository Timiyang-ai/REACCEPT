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
                for (final String tag : commandCriteria) {
                    predicates.add(cb.isMember(tag, commands.get(CommandEntity_.tags)));

                }
            }

            if (clusterCriteria != null) {
                for (final String tag : clusterCriteria.getTags()) {
                    predicates.add(cb.isMember(tag, root.get(ClusterEntity_.tags)));
                }
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }