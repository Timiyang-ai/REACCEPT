public static boolean isTransitionGroup(@NonNull ViewGroup group) {
        if (Build.VERSION.SDK_INT >= 21) {
            return group.isTransitionGroup();
        }
        Boolean explicit = (Boolean) group.getTag(R.id.tag_transition_group);
        return (explicit != null && explicit)
                || group.getBackground() != null
                || ViewCompat.getTransitionName(group) != null;
    }