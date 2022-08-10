package darius.com.model;

public enum TrainClass {

    SL(Class.SL),
    AC(Class.AC);
    private final String roleType;

    TrainClass(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public static class Class {
        public static final String SL = "SL";
        public static final String AC = "AC";

    }
}
