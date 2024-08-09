public State result(List<ActionSchema> actions){
        State resultState = new State(this.getFluents());
        for (ActionSchema action :
                actions) {
            resultState = resultState.result(action);
        }
        return resultState;
    }