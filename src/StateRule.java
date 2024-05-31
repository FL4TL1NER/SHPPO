public class StateRule {
    private String newStateName;
    private Event changeCondition;

    public StateRule(String newStateName, Event changeCondition) {
        this.newStateName = newStateName;
        this.changeCondition = changeCondition;
    }

    public String getNewStateName() {
        return newStateName;
    }

    public Event getChangeCondition() {
        return changeCondition;
    }

    public void setChangeCondition(Event changeCondition) {
        this.changeCondition = changeCondition;
    }
}
