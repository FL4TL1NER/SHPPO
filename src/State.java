import java.util.ArrayList;

public class State {
    private String name;
    private ArrayList<StateRule> rules;
    private MyRunnable job;

    public State(String name,MyRunnable job)
    {
        this.name = name;
        this.job = job;
        rules = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    private StateRule findStateRule(String name)
    {
        StateRule res = null;
        //if(!rules.isEmpty())
            for(StateRule i : rules)
            {
                if(i.getNewStateName().equals(name))
                {
                    res = i;
                }
            }
        return res;
    }

    public void addRule(StateRule rule)
    {
        StateRule res = findStateRule(name);
        if(res==null)
        {
            rules.add(rule);
        }
    }

    public void changeRule(String name,Event condition)
    {
        StateRule res = findStateRule(name);
        if(res!=null)
        {
            res.setChangeCondition(condition);
        }
    }

    public void removeRule(String name)
    {
        StateRule res = findStateRule(name);
        if(res!=null)
        {
            rules.remove(res);
        }
    }

    public String getNextState(Event event)
    {
        if(!rules.isEmpty())
            for(StateRule i:rules)
            {
                if(i.getChangeCondition().equals(event))
                {
                    return i.getNewStateName();
                }
            }
        return null;
    }

    public MyRunnable getJob()
    {
        return job;
    }
}
