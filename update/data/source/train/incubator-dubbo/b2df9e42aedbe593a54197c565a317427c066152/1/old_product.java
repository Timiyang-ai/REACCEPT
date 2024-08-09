public static List<String> revertForbid(List<String> forbid, Set<String> subscribed) {
        if (forbid != null && forbid.size() > 0) {
            List<String> newForbid = new ArrayList<String>();
            for (String serviceName : forbid) {
                if (!serviceName.contains(":") && !serviceName.contains("/")) {
                    for (String name : subscribed) {
                        if (name.contains(serviceName)) {
                            newForbid.add(name);
                            break;
                        }
                    }
                } else {
                    newForbid.add(serviceName);
                }
            }
            return newForbid;
        }
        return forbid;
    }