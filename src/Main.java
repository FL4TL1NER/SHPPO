import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBeanConfig.class);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        GlobalVariables globalVariables = context.getBean(GlobalVariables.class);
        FiniteStateMachine finiteStateMachine = context.getBean(FiniteStateMachine.class);
        ThreadPoolExecutorFacade threadPool = context.getBean(ThreadPoolExecutorFacade.class);

        globalVariables.subscribe(finiteStateMachine);
        finiteStateMachine.subscribe(threadPool);
        threadPool.subscribe(finiteStateMachine);

        globalVariables.addGVar("water", "false");
        globalVariables.addGVar("jump", "false");

        finiteStateMachine.addState(context.getBean("stand", State.class));
        finiteStateMachine.addState(context.getBean("air", State.class));
        finiteStateMachine.addState(context.getBean("swim", State.class));

        finiteStateMachine.addRule("stand", context.getBean("toswim", StateRule.class));
        finiteStateMachine.addRule("swim", context.getBean("fromswim", StateRule.class));
        finiteStateMachine.addRule("stand", context.getBean("toair", StateRule.class));
        finiteStateMachine.addRule("air", context.getBean("fromair", StateRule.class));

        LoggingAspect a = context.getBean(LoggingAspect.class);

        context.close();

        ApplicationMenu applicationMenu = new ApplicationMenu(globalVariables, finiteStateMachine);
        applicationMenu.StartApplicationMenu();
    }
}