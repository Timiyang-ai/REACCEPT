@Test
    public void testRestoreItem() throws Exception
    {
        context.turnOffAuthorisationSystem();
        String handle = "1345/567";
        Collection col = Collection.create(context);
        WorkspaceItem is = WorkspaceItem.create(context, col, false);

        //get current date
        DCDate now = DCDate.getCurrent();
        String dayAndTime = now.toString();
        //parse out just the date, remove the time (format: yyyy-mm-ddT00:00:00Z)
        String date = dayAndTime.substring(0, dayAndTime.indexOf("T"));

        //Build the beginning of a dummy provenance message
        //(restoreItem should NEVER insert a provenance message with today's date)
        String provDescriptionBegins = "Made available in DSpace on " + date;
        
        Item result = InstallItem.restoreItem(context, is, handle);
        context.restoreAuthSystemState();

        //Make sure restore worked
        assertThat("testRestoreItem 0", result, equalTo(is.getItem()));

        //Make sure that restore did NOT insert a new provenance message with today's date
        DCValue[] provMsgValues = result.getMetadata("dc", "description", "provenance", Item.ANY);
        int i = 1;
        for(DCValue val : provMsgValues)
        {
            assertFalse("testRestoreItem " + i, val.value.startsWith(provDescriptionBegins));
            i++;
        }
    }