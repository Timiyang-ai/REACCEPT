@SuppressWarnings({"BroadCatchBlock", "UnusedAssignment"})
    private static int main0(String[] args0) {
        List<String> args = new ArrayList<>(asList(args0)); // list must be mutable b/c myCapsule() might mutate it
        Capsule capsule = null;
        try {
            capsule = myCapsule(args);

            args = unmodifiableList(args);

            if (isWrapperFactoryCapsule(capsule)) {
                capsule = null; // help gc
                return runOtherCapsule(args);
            }

            if (runActions(capsule, args))
                return 0;

            return capsule.launch(args);
        } catch (Throwable t) {
            if (capsule != null) {
                capsule.cleanup1();
                capsule.onError(t);
            } else
                printError(t, capsule);
            return 1;
        }
    }