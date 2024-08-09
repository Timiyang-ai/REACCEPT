public void setWorkflowGroup(int step, Group newGroup)
            throws SQLException, AuthorizeException
    {
        Group oldGroup = getWorkflowGroup(step);
        String stepColumn;
        int action;
        switch(step)
        {
        case 1:
            action = Constants.WORKFLOW_STEP_1;
            stepColumn = "workflow_step_1";
            break;
        case 2:
            action = Constants.WORKFLOW_STEP_2;
            stepColumn = "workflow_step_2";
            break;
        case 3:
            action = Constants.WORKFLOW_STEP_3;
            stepColumn = "workflow_step_3";
            break;
        default:
            throw new IllegalArgumentException("Illegal step count:  " + step);
        }
        workflowGroup[step-1] = newGroup;
        if (newGroup != null)
            collectionRow.setColumn(stepColumn, newGroup.getID());
        else
            collectionRow.setColumnNull(stepColumn);
        modified = true;

        // Deal with permissions.
        try {
            ourContext.turnOffAuthorisationSystem();
            // remove the policies for the old group
            if (oldGroup != null)
            {
                List<ResourcePolicy> oldPolicies = AuthorizeManager
                        .getPoliciesActionFilter(ourContext, this, action);
                int oldGroupID = oldGroup.getID();
                for (ResourcePolicy rp : oldPolicies)
                {
                    if (rp.getGroupID() == oldGroupID)
                        rp.delete();
                }

                oldPolicies = AuthorizeManager
                        .getPoliciesActionFilter(ourContext, this, Constants.ADD);
                for (ResourcePolicy rp : oldPolicies)
                {
                    if ((rp.getGroupID() == oldGroupID)
                            && ResourcePolicy.TYPE_WORKFLOW.equals(rp.getRpType()))
                        rp.delete();
                }
           }

            // New group can be null to delete workflow step.
            // We need to grant permissions if new group is not null.
            if (newGroup != null)
            {
                AuthorizeManager.addPolicy(ourContext, this, action, newGroup,
                        ResourcePolicy.TYPE_WORKFLOW);
                AuthorizeManager.addPolicy(ourContext, this, Constants.ADD, newGroup,
                        ResourcePolicy.TYPE_WORKFLOW);
            }
        } finally {
            ourContext.restoreAuthSystemState();
        }
    }