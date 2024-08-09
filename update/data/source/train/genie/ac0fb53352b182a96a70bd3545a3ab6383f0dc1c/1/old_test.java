@Test
    public void testFindByClusterAndCommandCriteria() throws GenieException {
        final ClusterCriteria criteria = new ClusterCriteria(CLUSTER_CRITERIA_TAGS);
        final Specification<ClusterEntity> spec = JpaClusterSpecs
                .findByClusterAndCommandCriteria(criteria, COMMAND_CRITERIA);

        spec.toPredicate(this.root, this.cq, this.cb);
        Mockito.verify(this.cq, Mockito.times(1)).distinct(true);
        Mockito.verify(this.commands, Mockito.times(1)).get(CommandEntity_.status);
        Mockito.verify(this.cb, Mockito.times(1))
                .equal(this.commands.get(CommandEntity_.status), CommandStatus.ACTIVE);
        Mockito.verify(this.root, Mockito.times(1)).get(ClusterEntity_.status);
        Mockito.verify(this.cb, Mockito.times(1))
                .equal(this.root.get(ClusterEntity_.status), ClusterStatus.UP);
        Mockito.verify(this.commands, Mockito.times(COMMAND_CRITERIA.size()))
                .get(CommandEntity_.tags);
        Mockito.verify(this.root, Mockito.times(CLUSTER_CRITERIA_TAGS.size()))
                .get(ClusterEntity_.tags);
        Mockito.verify(
                this.cb,
                Mockito.times(COMMAND_CRITERIA.size() + CLUSTER_CRITERIA_TAGS.size()))
                .isMember(Mockito.any(String.class), Mockito.any(Expression.class));
    }