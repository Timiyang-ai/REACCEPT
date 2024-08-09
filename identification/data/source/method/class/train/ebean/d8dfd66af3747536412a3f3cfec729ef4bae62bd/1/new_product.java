public List<Object> findIdsByParentId(Object parentId, List<Object> parentIdList, Transaction t, List<Object> excludeDetailIds, boolean hard) {
    if (parentId != null) {
      return sqlHelp.findIdsByParentId(parentId, t, excludeDetailIds, hard);
    } else {
      return sqlHelp.findIdsByParentIdList(parentIdList, t, excludeDetailIds, hard);
    }
  }