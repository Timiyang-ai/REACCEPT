protected List<Map<String, Object>> toMap(Collection<Assignment> assignments) {
        List<Map<String, Object>> responseList = new ArrayList<>();
        assignments.forEach(assignment -> {
            responseList.add(toMap(assignment));
        });
        return responseList;
    }