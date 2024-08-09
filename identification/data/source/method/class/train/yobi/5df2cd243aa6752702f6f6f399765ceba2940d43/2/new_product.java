public static List<Milestone> findMilestones(Long projectId, MilestoneState state, String sort,
                                                 final Direction direction) {
        List<Milestone> milestones = null;
        if (sort.equals("dueDate")) { // 완료일(dueDate) 기준 정렬
            milestones = findMilestones(projectId, state, direction);
        } else if (sort.equals("completionRate")) {
            milestones = findMilestones(projectId, state);
            Collections.sort(milestones, new Comparator<Milestone>() {
                @Override
                public int compare(Milestone m1, Milestone m2) {
                    if (direction == Direction.ASC) {
                        return m1.getCompletionRate() - m2.getCompletionRate();
                    } else {
                        return m2.getCompletionRate() - m1.getCompletionRate();
                    }
                }
            });
        }
        return milestones;
    }