public static List<String> revertForbid(List<String> forbid, Set<URL> subscribed) {
        if (forbid != null && forbid.size() > 0) {
            List<String> newForbid = new ArrayList<String>();
            for (String serviceName : forbid) {
                if (! serviceName.contains(":") && ! serviceName.contains("/")) {
                    for (URL url : subscribed) {
                        if (serviceName.equals(url.getServiceInterface())) {
                            newForbid.add(url.getServiceKey());
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