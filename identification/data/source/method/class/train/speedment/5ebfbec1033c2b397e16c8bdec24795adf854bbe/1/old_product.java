public static boolean isSame(Table first, Table second) {
        if (first.getName().equals(second.getName())) {
            final Schema firstParent  = first.getParentOrThrow();
            final Schema secondParent = second.getParentOrThrow();
            return isSame(firstParent, secondParent);
        } else return false;
    }