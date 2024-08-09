@SuppressWarnings("unchecked")
    private static boolean runActions(Capsule capsule, List<String> args) {
        try {
            boolean found = false;
            for (Map.Entry<String, Object[]> entry : OPTIONS.entrySet()) {
                if (!capsule.isWrapperCapsule() && (Boolean) entry.getValue()[OPTION_WRAPPER_ONLY])
                    continue;
                if (entry.getValue()[OPTION_METHOD] != null && systemPropertyEmptyOrTrue(entry.getKey())) {
                    final Method m = getMethod(capsule, (String) entry.getValue()[OPTION_METHOD], List.class);
                    m.invoke(capsule.cc.sup((Class<? extends Capsule>) m.getDeclaringClass()), args);
                    found = true;
                }
            }
            return found;
        } catch (InvocationTargetException e) {
            throw rethrow(e);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(e);
        }
    }