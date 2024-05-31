public class StateBuilder {
    public String name;
    public MyRunnable job;

    public State build()
    {
        return new State(name,job);
    }
}
