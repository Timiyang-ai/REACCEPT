public static ThreadFactory groupedThreads(String groupName, String pattern) {
        return groupedThreads(groupName, pattern, log);
    }