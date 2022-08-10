package darius.com.model;

public enum TrainClass {

    SL(Class.SL),
    AC(Class.AC);
    private final String classType;

    TrainClass(String classType) {
        this.classType = classType;
    }

    public String getClassType() {
        return this.classType;
    }

    public static class Class {
        public static final String SL = "SL";
        public static final String AC = "AC";

    }
}
