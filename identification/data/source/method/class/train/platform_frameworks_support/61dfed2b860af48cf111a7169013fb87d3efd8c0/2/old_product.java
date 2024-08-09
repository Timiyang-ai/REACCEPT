public boolean isTransitionGroup(ViewGroup group) {
            Boolean explicit = (Boolean) group.getTag(R.id.tag_transition_group);
            return (explicit != null && explicit)
                    || group.getBackground() != null
                    || ViewCompat.getTransitionName(group) != null;
        }