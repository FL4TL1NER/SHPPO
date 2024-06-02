import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(".")
public class ScanBeanConfig {
    @Bean
    @Scope("singleton")
    FiniteStateMachine fsm()
    {
        return new FiniteStateMachine();
    }
    @Bean
    @Scope("singleton")
    GlobalVariables gVars()
    {
        return new GlobalVariables();
    }
    @Bean
    @Scope("singleton")
    ThreadPoolExecutorFacade threadPool()
    {
        return new ThreadPoolExecutorFacade();
    }

    @Bean
    public State stand()
    {
        return new State("stand",new Runnable3());
    }
    @Bean
    public State air()
    {
        return new State("air",new Runnable1());
    }
    @Bean
    public State swim()
    {
        return new State("swim",new Runnable2());
    }

    @Bean
    public StateRule toswim()
    {
        return new StateRule("swim",new EventGVarChange("water","true"));
    }
    @Bean
    public StateRule fromswim()
    {
        return new StateRule("stand",new EventGVarChange("water","false"));
    }
    @Bean
    public StateRule toair()
    {
        return new StateRule("air",new EventGVarChange("jump","true"));
    }
    @Bean
    public StateRule fromair()
    {
        return new StateRule("stand",new EventGVarChange("jump","false"));
    }
    @Bean
    public LoggingAspect myLoggingAspect()
    {
        return new LoggingAspect();
    }
}
