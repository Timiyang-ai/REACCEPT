public List<Object> findIdsByParentId(Object parentId, List<Object> parentIdList, Transaction t, List<Object> excludeDetailIds) {
    if (parentId != null) {
      return sqlHelp.findIdsByParentId(parentId, t, excludeDetailIds);
    } else {
      return sqlHelp.findIdsByParentIdList(parentIdList, t, excludeDetailIds);
    }
  }