import java.lang.reflect.Array;

public class Kalender {
    private String name;
    private Termin[] termine;
    private Terminserie[] serien;
    private final int initialSize = 32;
    private int terminPosition = 0;
    private int serienPosition = 0;

    public Kalender(String name){
        if (name==null||name.isEmpty()){
            throw new IllegalArgumentException("Name cant be null or empty!");
        }
        this.name = name;
        termine = new Termin[initialSize];
        serien = new Terminserie[initialSize];
    }

    void setName(String name) {
        if (name==null||name.isEmpty()){
            throw new IllegalArgumentException("Name cant be null or empty!");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void addTermin(Termin termin){
        if (termin == null) {throw new NullPointerException();}
        if (termine == null) {termine = new Termin[initialSize];}
        if(terminPosition == termine.length){
            termine = makeArrayBigger(termine, Termin.class);
        }
        termine[terminPosition++] = termin;
    }

    void addSerie(Terminserie terminserie){
        if (terminserie == null) {throw new NullPointerException();}
        if (serien == null) {serien = new Terminserie[initialSize];}
        if (serien.length==serienPosition) {serien = makeArrayBigger(serien, Terminserie.class);}
        serien[serienPosition++] = terminserie;
    }

    @Override
    public String toString(){
        if(termine==null){return "";}
        StringBuilder stringBuilder = new StringBuilder();
        if(name!=null){
            stringBuilder.append(name).append(":\n");
        }
        for (int i = 0;i<terminPosition;i++){
            stringBuilder.append(termine[i]).append("\n");
        }
        for (int i = 0;i<serienPosition;i++){
            for (int index = 0; index < serien[i].getNumber(); index++) {
                stringBuilder.append(serien[i].getTermin(index)).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private <T> T[] makeArrayBigger(T[] toIncrease, Class<T> elementType){
        T[] result = (T[]) Array.newInstance(elementType, toIncrease.length * 2);
        for (int i = 0; i < toIncrease.length; i++) {
            result[i] = toIncrease[i];
        }
        return result;
    }
}
