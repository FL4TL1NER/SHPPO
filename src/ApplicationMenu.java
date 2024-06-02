import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ApplicationMenu {
    private FiniteStateMachine fsm;
    private GlobalVariables globalVariables;
    private Scanner in;
    private String input;
    private boolean isRunning;
    @FunctionalInterface
    interface Procedure{
        void func();
    }
    Map<String,Procedure> menuMap = new HashMap<>();
    public ApplicationMenu(GlobalVariables globalVariables, FiniteStateMachine fsm)
    {
        this.fsm = fsm;
        this.globalVariables = globalVariables;
        this.in = new Scanner(System.in);
        menuMap.put("create_state", this::createState);
        menuMap.put("create_gvar", this::createGvar);
        menuMap.put("create_rule", this::createRule);
        menuMap.put("change_gvar", this::changeGvar);
        menuMap.put("delete_state", this::deleteState);
        menuMap.put("delete_gvar", this::deleteGvar);
        menuMap.put("delete_rule", this::deleteRule);
        menuMap.put("get_active_state", this::getActiveState);
        menuMap.put("get_gvar", this::getGvar);
        menuMap.put("get_rules", this::getRules);
        menuMap.put("exit", this::exit);
    }
    public void StartApplicationMenu()
    {
        isRunning = true;
        while(isRunning)
        {
            System.out.print("input command: ");
            input = in.nextLine();

            if(menuMap.containsKey(input))
            {
                menuMap.get(input).func();
            }
        }
    }

    private void exit()
    {
        isRunning = false;
    }
    private void createState()
    {
        StateBuilder stateBuilder = new StateBuilder();

        System.out.print("enter name: ");
        stateBuilder.name = in.nextLine();

        int c;
        System.out.print("enter n of job: ");
        c = Integer.parseInt(in.nextLine());
        switch (c)
        {
            case 1:
                stateBuilder.job = new Runnable1();
                break;
            case 2:
                stateBuilder.job = new Runnable2();
                break;
            default:
                stateBuilder.job = new Runnable3();
                break;
        }

        fsm.addState(stateBuilder.build());
    }

    private void createRule()
    {
        System.out.print("enter name of state: ");
        String stateName = in.nextLine();

        StateRuleBuilder stateRuleBuilder = new StateRuleBuilder();

        System.out.print("enter name of next state: ");
        stateRuleBuilder.newStateName = in.nextLine();

        System.out.print("enter type of event[gvar/end]: ");
        input = in.nextLine();
        Event event;
        if(input.equals("end"))
        {
            int c;
            System.out.print("enter n of job: ");
            c = Integer.parseInt(in.nextLine());
            event = switch (c) {
                case 1 -> new EventEndAlg(1);
                case 2 -> new EventEndAlg(2);
                default -> new EventEndAlg(3);
            };
        }
        else
        {
            System.out.print("enter name of gvar: ");
            String name = in.nextLine();
            System.out.print("enter data: ");
            String data = in.nextLine();

            event = new EventGVarChange(name,data);
        }

        stateRuleBuilder.changeCondition = event;

        fsm.addRule(stateName,stateRuleBuilder.build());
    }

    private void createGvar()
    {
        System.out.print("enter name: ");
        String name = in.nextLine();

        System.out.print("enter data: ");
        String data = in.nextLine();

        globalVariables.addGVar(name,data);
    }

    private void changeGvar()
    {
        System.out.print("enter name of gvar: ");
        String name = in.nextLine();
        System.out.print("enter data: ");
        String data = in.nextLine();

        globalVariables.setGVar(name,data);
    }

    private void deleteState()
    {
        System.out.print("enter name of state: ");
        String name = in.nextLine();

        fsm.removeState(name);
    }

    private void deleteGvar()
    {
        System.out.print("enter name of gvar: ");
        String name = in.nextLine();

        globalVariables.deleteGVar(name);
    }

    private void deleteRule()
    {
        System.out.print("enter name of rule: ");
        String name = in.nextLine();
        System.out.print("enter second name of rule: ");
        String name2 = in.nextLine();

        fsm.removeRule(name,name2);
    }

    private void getActiveState()
    {
        System.out.println(fsm.getActiveState());
    }

    private void getGvar()
    {
        System.out.print("enter name of gvar: ");
        String name = in.nextLine();

        System.out.println(globalVariables.getDataGVar(name));
    }

    private void getRules()
    {
        System.out.println("enter name of state: ");
    }
}
