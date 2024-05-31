import java.util.Scanner;

public class ApplicationMenu {
    public static void StartApplicationMenu(GlobalVariables globalVariables, FiniteStateMachine fsm)
    {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning)
        {
            System.out.print("input command: ");
            String input = in.nextLine();
            // state manipulation logic
            if(input.equals("create_state"))
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
            if(input.equals("create_gvar"))
            {
                System.out.print("enter name: ");
                String name = in.nextLine();

                System.out.print("enter data: ");
                String data = in.nextLine();

                globalVariables.addGVar(name,data);
            }
            if(input.equals("create_rule"))
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
            // global variables logic
            if(input.equals("change_gvar"))
            {
                System.out.print("enter name of gvar: ");
                String name = in.nextLine();
                System.out.print("enter data: ");
                String data = in.nextLine();

                globalVariables.setGVar(name,data);
            }

            if(input.equals("delete_state"))
            {
                System.out.print("enter name of state: ");
                String name = in.nextLine();

                fsm.removeState(name);
            }
            if(input.equals("delete_gvar"))
            {
                System.out.print("enter name of gvar: ");
                String name = in.nextLine();

                globalVariables.deleteGVar(name);
            }
            if(input.equals("delete_rule"))
            {
                System.out.print("enter name of rule: ");
                String name = in.nextLine();
                System.out.print("enter second name of rule: ");
                String name2 = in.nextLine();

                fsm.removeRule(name,name2);
            }
            // state logic
            if(input.equals("get_active_state"))
            {
                System.out.println(fsm.getActiveState());
            }

            if(input.equals("get_gvar"))
            {
                System.out.print("enter name of gvar: ");
                String name = in.nextLine();

                System.out.println(globalVariables.getDataGVar(name));
            }

            if(input.equals("get_rules"))
            {
                System.out.println("enter name of state: ");
            }

            if(input.equals("exit"))
            {
                isRunning = false;
            }
        }
    }
}
