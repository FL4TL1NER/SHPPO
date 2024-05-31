import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class FiniteStateMachine {
    /*private static FiniteStateMachine instance = null;
    static FiniteStateMachine getInstance()
    {
        if(instance==null)
        {
            instance = new FiniteStateMachine();
        }
        return instance;
    }*/

    ArrayList<State> states;
    State activeState;
    ThreadPoolExecutorFacade threadPool;

    public FiniteStateMachine()
    {
        states = new ArrayList<>();
        activeState = null;
    }

    public void subscribe(ThreadPoolExecutorFacade threadPool)
    {
        this.threadPool = threadPool;
    }

    private State findState(String name)
    {
        State result = null;
        if(!states.isEmpty())
            for(State i: states)
            {
                if(i.getName().equals(name))
                {
                    result = i;
                }
            }
        return result;
    }

    public void addState(State a)
    {
        if(findState(a.getName())==null)
        {
            states.add(a);
        }
        if(activeState==null)
        {
            activeState = states.getLast();
        }
    }

    public void removeState(String name)
    {
        State result = findState(name);
        if(result!=null)
        {
            states.remove(result);
        }
        if(activeState==result)
        {
            if(states.isEmpty())
            {
                activeState = null;
            }
            else
            {
                activeState = states.getFirst();
            }
        }
    }

    public void addRule(String name,StateRule rule)
    {
        State result = findState(name);
        if(result!=null)
        {
            result.addRule(rule);
        }
    }

    public void removeRule(String name,String changeTo)
    {
        State result = findState(name);
        if(result!=null)
        {
            result.removeRule(changeTo);
        }
    }

    public String getActiveState()
    {
        if(activeState!=null)
        {
            return activeState.getName();
        }
        return null;
    }

    public void handleEvent(Event event)
    {
        String result = activeState.getNextState(event);
        if(result != null)
        {
            setState(result);
            threadPool.executeJob(activeState.getJob());
        }
    }

    private void setState(String newState)
    {
        activeState = findState(newState);
    }
}
