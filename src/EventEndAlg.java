import java.util.Objects;

public class EventEndAlg extends Event{
    int n;

    public EventEndAlg(int n)
    {
        super(EventType.AlgEnded);
        this.n = n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEndAlg that = (EventEndAlg) o;
        return n == that.n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(n);
    }
}
