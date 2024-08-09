public static void setTransitionGroup(@NonNull ViewGroup group, boolean isTransitionGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            group.setTransitionGroup(isTransitionGroup);
        } else {
            group.setTag(R.id.tag_transition_group, isTransitionGroup);
        }
    }