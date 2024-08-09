private Map<Integer, List<Permission>> getPrintablePermissionMap(List<Permission> perms) {
        ConcurrentHashMap<Integer, List<Permission>> sortedMap = new ConcurrentHashMap<>();
        sortedMap.put(0, new ArrayList());
        sortedMap.put(1, new ArrayList());
        sortedMap.put(2, new ArrayList());
        sortedMap.put(3, new ArrayList());
        sortedMap.put(4, new ArrayList());
        for (Permission perm : perms) {
            if (perm instanceof ServicePermission) {
                if (DefaultPolicyBuilder.getNBServiceList().contains(perm.getName())) {
                    if (perm.getName().contains("Admin")) {
                        sortedMap.get(1).add(perm);
                    } else {
                        sortedMap.get(2).add(perm);
                    }
                } else {
                    sortedMap.get(3).add(perm);
                }
            } else if (perm instanceof AppPermission) {
                sortedMap.get(0).add(perm);
            } else {
                sortedMap.get(4).add(perm);
            }
        }
        return sortedMap;
    }