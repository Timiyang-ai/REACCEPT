    private WorkerCommand<Void,Integer> evaluate( final LazySingleReference<Integer> ref )
    {
        return state -> ref.get();
    }