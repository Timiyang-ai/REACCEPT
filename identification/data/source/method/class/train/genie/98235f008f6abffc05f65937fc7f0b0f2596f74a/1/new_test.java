@Test
    public void testFindByClusterAndCommandCriteria() throws GenieException {
        final ClusterCriteria criteria = new ClusterCriteria(CLUSTER_CRITERIA_TAGS);
        final Specification<ClusterEntity> spec
                = JpaClusterSpecs.findByClusterAndCommandCriteria(criteria, COMMAND_CRITERIA);

        spec.toPredicate(this.root, this.cq, this.cb);
        Mockito.verify(this.cq, Mockito.times(1)).distinct(true);
        Mockito.verify(this.cb, Mockito.times(1))
                .equal(Mockito.eq(this.commands.get(CommandEntity_.status)), Mockito.eq(CommandStatus.ACTIVE));
        Mockito.verify(this.cb, Mockito.times(1))
                .equal(Mockito.eq(this.root.get(ClusterEntity_.status)), Mockito.eq(ClusterStatus.UP));
        Mockito.verify(this.cb, Mockito.times(1))
                .like(Mockito.eq(this.root.get(ClusterEntity_.tags)), Mockito.eq(this.tagLikeStatement));
        Mockito.verify(this.cb, Mockito.times(1))
                .like(Mockito.eq(this.commands.get(CommandEntity_.tags)), Mockito.eq(this.commandLikeStatement));
    }