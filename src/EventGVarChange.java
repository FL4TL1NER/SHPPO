import java.util.Objects;

public class EventGVarChange extends Event {
    private String gVarName;
    private String gVarNewData;

    public EventGVarChange(String name,String data)
    {
        super(EventType.GVarChange);
        this.gVarName = name;
        this.gVarNewData = data;
    }

    public String getgVarName() {
        return gVarName;
    }

    public String getgVarNewData() {
        return gVarNewData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventGVarChange that = (EventGVarChange) o;
        return Objects.equals(gVarName, that.gVarName) && Objects.equals(gVarNewData, that.gVarNewData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gVarName, gVarNewData);
    }
}
