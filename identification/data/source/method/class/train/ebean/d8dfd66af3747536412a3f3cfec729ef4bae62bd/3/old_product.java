public List<Object> findIdsByParentId(Object parentId, List<Object> parentIdist, Transaction t, ArrayList<Object> excludeDetailIds) {
    if (parentId != null) {
      return findIdsByParentId(parentId, t, excludeDetailIds);
    } else {
      return findIdsByParentIdList(parentIdist, t, excludeDetailIds);
    }
  }