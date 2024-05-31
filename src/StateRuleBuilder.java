public class StateRuleBuilder {
    public String newStateName;
    public Event changeCondition;

    public StateRule build()
    {
        return new StateRule(newStateName,changeCondition);
    }
}
