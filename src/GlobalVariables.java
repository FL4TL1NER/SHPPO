import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class GlobalVariables {
    /*private static GVars instance = null;
    static GVars getInstance()
    {
        if(instance==null)
        {
            instance = new GVars();
        }
        return instance;
    }*/

    private HashMap<String,String> globalVaribles;

    FiniteStateMachine finiteStateMachine;

    public GlobalVariables()
    {
        this.globalVaribles = new HashMap<>();
    }

    public void subscribe(FiniteStateMachine fsm)
    {
        this.finiteStateMachine = fsm;
    }

    public void addGVar(String name,String data)
    {
        if(!globalVaribles.containsKey(name))
        {
            globalVaribles.put(name,data);
        }
    }

    public void deleteGVar(String name)
    {
        globalVaribles.remove(name);
    }

    public void setGVar(String name,String data)
    {
        if(globalVaribles.containsKey(name))
        {
            globalVaribles.put(name,data);
            finiteStateMachine.handleEvent(new EventGVarChange(name,data));
        }
    }

    public String getDataGVar(String name)
    {
        if(globalVaribles.containsKey(name))
        {
            return globalVaribles.get(name);
        }
        return null;
    }
}
