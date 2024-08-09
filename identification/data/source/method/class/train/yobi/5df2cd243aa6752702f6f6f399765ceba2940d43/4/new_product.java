public static List<Milestone> findMilestones(Long projectId,
                                                 State state, String sort, final Direction direction) {
    	
    	OrderParams orderParams = new OrderParams();
    	
    	if(!"completionRate".equals(sort)) {
    		orderParams.add(sort, direction);	
    	}
    	    	
        SearchParams searchParams = new SearchParams().add("project.id", projectId, Matching.EQUALS);
        if(state != null && state != State.ALL) {
            searchParams.add("state", state, Matching.EQUALS);
        }
        
        List<Milestone> milestones = FinderTemplate.findBy(orderParams, searchParams, find);

        if("completionRate".equals(sort)) {
        	Collections.sort(milestones, new Comparator<Milestone>() {
				@Override
				public int compare(Milestone o1, Milestone o2) {
					int o1CompletionRate = o1.getCompletionRate();
					int o2CompletionRate = o2.getCompletionRate();
					
					if(direction == Direction.ASC) {
				        return (o1CompletionRate < o2CompletionRate ? -1 : (o1CompletionRate == o2CompletionRate ? 0 : 1));
					} else {
						return (o1CompletionRate < o2CompletionRate ? 1 : (o1CompletionRate == o2CompletionRate ? 0 : -1));
					}
				}
			});
        }
        
        return milestones;
    }