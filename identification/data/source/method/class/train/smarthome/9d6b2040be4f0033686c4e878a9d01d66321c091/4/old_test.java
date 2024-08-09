    private Timer createTimer(MockClosure0 closure) {
        Timer timer = ScriptExecution.createTimer(now(), closure);
        // The code in our mock closure needs access to the timer object
        closure.setTimer(timer);
        return timer;
    }