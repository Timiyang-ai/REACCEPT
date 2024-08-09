public List<Object> findIdsByParentId(Object parentId, List<Object> parentIdList, Transaction t, ArrayList<Object> excludeDetailIds) {
    if (parentId != null) {
      return findIdsByParentId(parentId, t, excludeDetailIds);
    } else {
      return findIdsByParentIdList(parentIdList, t, excludeDetailIds);
    }
  }