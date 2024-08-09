public boolean isProfilableClass(String className) {
        if (profileInclude.contains(className)) {
            return true;
        } else {
            String packageName = className.substring(0, className.lastIndexOf("/") + 1);
            for (String pkg : profileIncludeSub) {
                if (packageName.startsWith(pkg)) {
                    return true;
                }
            }
        }
        return false;
    }