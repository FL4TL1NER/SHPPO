public class Event {
    EventType type;

    public Event(EventType type)
    {
        this.type = type;
    }
}

enum EventType
{
    GVarChange,
    AlgEnded
}