private Map<Integer, List<Permission>> getPrintablePermissionMap(Set<Permission> perms) {
        ConcurrentHashMap<Integer, List<Permission>> sortedMap = new ConcurrentHashMap<>();
        sortedMap.put(0, new ArrayList());
        sortedMap.put(1, new ArrayList());
        sortedMap.put(2, new ArrayList());
        sortedMap.put(3, new ArrayList());
        sortedMap.put(4, new ArrayList());
        sortedMap.put(5, new ArrayList());
        sortedMap.put(6, new ArrayList());
        sortedMap.put(7, new ArrayList());

        for (Permission perm : perms) {
            if (perm instanceof AppPermission) {
                sortedMap.get(0).add(perm);
            } else if (perm instanceof ServicePermission) {
                String permName = perm.getName().trim();
                if (DefaultPolicyBuilder.getNBServiceList().contains(permName)) { // ONOS NB SERVICES
                    if (permName.contains("Admin")) {
                        sortedMap.get(1).add(perm);
                    } else {
                        sortedMap.get(2).add(perm);
                    }
                } else if (permName.contains("org.onosproject") && permName.contains("Provider")) { //ONOS SB SERVICES
                    sortedMap.get(3).add(perm);
                } else if (DefaultPolicyBuilder.getCliServiceList().contains(permName)) { //CLI SERVICES
                    sortedMap.get(4).add(perm);
                } else if (permName.contains("Security")) { //CRITICAL SERVICES
                    sortedMap.get(6).add(perm);
                } else {
                    sortedMap.get(5).add(perm);
                }
            } else if (perm instanceof RuntimePermission || perm instanceof SocketPermission ||
                    perm instanceof FilePermission || perm instanceof SecurityPermission ||
                    perm instanceof ReflectPermission) { // CRITICAL PERMISSIONS
                sortedMap.get(6).add(perm);
            } else {
                boolean isDefault = false;
                for (Permission dPerm : DefaultPolicyBuilder.getDefaultPerms()) {
                    if (perm.implies(dPerm)) {
                        isDefault = true;
                        break;
                    }
                }
                if (!isDefault) {
                    sortedMap.get(7).add(perm);
                }
            }
        }
        return sortedMap;
    }