import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.util.Optional;

@Component
@Aspect
public class LoggingAspect {// They are not working :(
    @Before("execution(private void FiniteStateMachine.setState(..))")
    public void afterChangeOfState(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        String name = object2String(args[0]).orElse("Неизвестный");
        System.out.println("new state is : " + args[0]);
    }
    @Before("execution(public void ThreadPoolExecutorFacade.executeJob(..))")
    public void afterExecutingTask(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        MyRunnable job = object2MyRunnable(args[0]).orElse(null);
        if(job!=null)
        {
            System.out.println("new job executed");
        }
        else
        {
            System.out.println("job is not MyRunnable");
        }
    }

    public Optional<String> object2String(Object obj) {
        if (obj instanceof String) return Optional.of((String) obj);
        return Optional.empty();
    }
    public Optional<MyRunnable> object2MyRunnable(Object obj) {
        if (obj instanceof MyRunnable) return Optional.of((MyRunnable) obj);
        return Optional.empty();
    }
}