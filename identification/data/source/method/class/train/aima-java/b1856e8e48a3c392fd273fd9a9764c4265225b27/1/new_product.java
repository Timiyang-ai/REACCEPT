public State result(List<ActionSchema> actions) {
        State resultState = new State(new ArrayList<>(this.getFluents()));
        for (ActionSchema action :
                actions) {
            resultState = resultState.result(action);
        }
        return resultState;
    }