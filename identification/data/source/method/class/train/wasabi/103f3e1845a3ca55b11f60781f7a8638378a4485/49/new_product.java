protected List<Map<String, Object>> toBatchAssignmentResponseMap(Collection<Assignment> assignments) {
        List<Map<String, Object>> responseList = new ArrayList<>();
        assignments.forEach(assignment -> {
            responseList.add(toBatchAssignmentResponseMap(assignment));
        });
        return responseList;
    }