public void setWorkflowGroup(int step, Group g)
    {
        workflowGroup[step - 1] = g;

        if (g == null)
        {
            collectionRow.setColumnNull("workflow_step_" + step);
        }
        else
        {
            collectionRow.setColumn("workflow_step_" + step, g.getID());
        }
        modified = true;
    }