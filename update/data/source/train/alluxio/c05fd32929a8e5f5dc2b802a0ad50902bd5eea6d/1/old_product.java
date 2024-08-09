public Set<AclAction> toAclActions() {
      Set<AclAction> actions = new HashSet<>();
      if (imply(READ)) {
        actions.add(AclAction.READ);
      }
      if (imply(WRITE)) {
        actions.add(AclAction.WRITE);
      }
      if (imply(EXECUTE)) {
        actions.add(AclAction.EXECUTE);
      }
      return actions;
    }