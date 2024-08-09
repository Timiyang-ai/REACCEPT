public static boolean isSame(Table first, Table second) {
        if (first.getId().equals(second.getId())) {
            final Schema firstParent  = first.getParentOrThrow();
            final Schema secondParent = second.getParentOrThrow();
            return isSame(firstParent, secondParent);
        } else {
            return false;
        }
    }