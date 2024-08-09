public State toNextState(){
        this.state = nextState();
        this.updatedDate = JodaDateUtil.now();
        super.update();
        return this.state;
    }