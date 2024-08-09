    private Map<Integer, List<Permission>> getPrintablePermissionMap(Set<Permission> perms) {
        ConcurrentHashMap<Integer, List<Permission>> sortedMap = new ConcurrentHashMap<>();
        sortedMap.put(0, new ArrayList());
        sortedMap.put(1, new ArrayList());
        sortedMap.put(2, new ArrayList());
        sortedMap.put(3, new ArrayList());
        sortedMap.put(4, new ArrayList());
        for (Permission perm : perms) {
            if (perm.getName().contains("Admin")) {
                sortedMap.get(1).add(perm);
            } else {
                sortedMap.get(2).add(perm);
            }
        }
        return sortedMap;
    }