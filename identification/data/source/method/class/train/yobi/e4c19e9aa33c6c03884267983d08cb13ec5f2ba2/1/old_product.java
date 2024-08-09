public State toNextState(){
        this.state = nextState();
        super.update();
        return this.state;
    }