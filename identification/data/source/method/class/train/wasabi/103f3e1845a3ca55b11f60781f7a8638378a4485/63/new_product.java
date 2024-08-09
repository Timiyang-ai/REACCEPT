protected Map<String, Object> toMap(final Assignment assignment) {
        Map<String, Object> response = newHashMap();

        if(nonNull(assignment.getExperimentLabel())) {
            response.put("experimentLabel", assignment.getExperimentLabel());
        }

        // Only include `assignment` property if there is a definitive assignment, either to a bucket or not
        if (assignment.getStatus().isCacheable() && assignment.getStatus() != EXPERIMENT_EXPIRED) {
            response.put("assignment", nonNull(assignment.getBucketLabel()) ? assignment.getBucketLabel().toString() : null);

            if (nonNull(assignment.getBucketLabel())) {
                response.put("payload", assignment.getPayload());
            }
        }

        response.put("status", assignment.getStatus());
        response.put("cache", assignment.getStatus().isCacheable());

        if (assignment.getContext() != null) {
            response.put("context", assignment.getContext().toString());
        }

        return response;
    }