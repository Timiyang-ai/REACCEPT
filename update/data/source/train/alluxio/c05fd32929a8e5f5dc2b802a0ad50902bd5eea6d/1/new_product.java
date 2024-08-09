public AclActions toAclActions() {
      AclActions actions = new AclActions();
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