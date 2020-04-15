package fr.eni.abono.bo;

public enum Frequency {
    DAILY( 0.00273972602f),
    WEEKLY( 0.01917806642f),
    MONTHLY( 0.08333333333f),
    QUARTERLY( 0.25f),
    SEMESTERLY( 0.5f),
    ANNUALLY( 1.f);

    private float value;

    Frequency(float value){
        this.value = value;
    }

    public float getValue(){
        return value;
    }
}
