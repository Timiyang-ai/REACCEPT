protected Map<String, Object> toBatchAssignmentResponseMap(final Assignment assignment) {
        Map<String, Object> response = newHashMap();

        //Add experimentLabel for batch-assignment flow only
        if(nonNull(assignment.getExperimentLabel())) {
            response.put("experimentLabel", assignment.getExperimentLabel());
        }

        // Only include `assignment` property if there is a definitive assignment, either to a bucket or not
        if (assignment.getStatus() != EXPERIMENT_EXPIRED) {
            response.put("assignment", nonNull(assignment.getBucketLabel()) ? assignment.getBucketLabel().toString() : null);

            if (nonNull(assignment.getBucketLabel())) {
                response.put("payload", assignment.getPayload());
            }
        }

        response.put("status", assignment.getStatus());

        return response;
    }