protected List<Map<String, Object>> toMap(Collection<Assignment> assignments, boolean isSingleAssignment) {
        List<Map<String, Object>> responseList = new ArrayList<>();
        assignments.forEach(assignment -> {
            responseList.add(toMap(assignment, isSingleAssignment));
        });
        return responseList;
    }