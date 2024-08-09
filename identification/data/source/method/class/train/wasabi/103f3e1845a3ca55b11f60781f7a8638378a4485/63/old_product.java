private Map<String, Object> toMap(final Assignment assignment) {
        Map<String, Object> response = newHashMap();

        // Only include `assignment` property if there is a definitive assignment, either to a bucket or not
        if (assignment.getStatus().isCacheable() && assignment.getStatus() != EXPERIMENT_EXPIRED) {
            response.put("assignment",
                    assignment.getBucketLabel() != null ? assignment.getBucketLabel().toString() : null);

            if (assignment.getBucketLabel() != null) {
                Bucket bucket = assignments.getBucket(assignment.getExperimentID(), assignment.getBucketLabel());

                response.put("payload", bucket.getPayload() != null ? bucket.getPayload() : null);
            }
        }

        response.put("status", assignment.getStatus());
        response.put("cache", assignment.getStatus().isCacheable());

        if (assignment.getContext() != null) {
            response.put("context", assignment.getContext().toString());
        }

        return response;
    }