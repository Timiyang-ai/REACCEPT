  private PrimaryTerm enter(String nodeId, String groupId, PrimaryElectorService elector) {
    PartitionId partId = new PartitionId("test", 1);
    GroupMember member = createGroupMember(nodeId, groupId);
    Session session = createSession(member);
    return elector.enter(createEnterOp(partId, member, session));
  }