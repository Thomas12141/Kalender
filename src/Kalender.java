import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Kalender {
    private String name;
    private Termin[] termine;
    private Terminserie[] serien;
    private final int initialSize = 32;
    private int terminCounter = 0;
    private int serienCounter = 0;

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
        if (termin == null) {
            throw new NullPointerException();
        }
        if(terminCounter == termine.length){
            termine = makeArrayBigger(termine, Termin.class);
        }
        termine[terminCounter++] = termin;
    }

    void addSerie(Terminserie terminserie){
        if (terminserie == null) {throw new NullPointerException();}
        if (serien == null) {serien = new Terminserie[initialSize];}
        if (serien.length== serienCounter) {serien = makeArrayBigger(serien, Terminserie.class);}
        serien[serienCounter++] = terminserie;
    }

    @Override
    public String toString(){
        if(termine==null){return "";}
        StringBuilder stringBuilder = new StringBuilder();
        if(name!=null){
            stringBuilder.append("\t").append(name).append(":\n");
        }
        for (int i = 0; i< terminCounter; i++){
            stringBuilder.append(termine[i]).append("\n");
        }
        for (int i = 0; i< serienCounter; i++){
            for (int index = 0; index < serien[i].getNumber(); index++) {
                stringBuilder.append(serien[i].getTermin(index)).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private <T> T[] makeArrayBigger(T[] toIncrease, Class<T> elementType){
        T[] result = (T[]) Array.newInstance(elementType, toIncrease.length * 2);
        System.arraycopy(toIncrease, 0, result, 0, toIncrease.length);
        return result;
    }

    public Termin[] freieTermineFinden(Kalender kalender, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int dauer, String name) {
        ArrayList<Termin> termins = new ArrayList<>();
        ArrayList<Termin> result = new ArrayList<>();
        addItems(termins, termine);
        ArrayList<Terminserie> series = new ArrayList<>(List.of(serien));
        for (Terminserie terminserie: series){
            termins.addAll(List.of(terminserie.getTermine()));
        }
        termins.addAll(List.of(kalender.termine));
        series = new ArrayList<>(List.of(kalender.serien));
        for (Terminserie terminserie: series){
            termins.addAll(List.of(terminserie.getTermine()));
        }
        termins.removeIf(termin -> isInTheTimeFrame(startDate, endDate, startTime, endTime, termin.getStart(), termin.getEnd()) || termin.getLength() != dauer);
        LocalDateTime localDateTime = LocalDateTime.of(startDate, startTime);
        while (localDateTime.toLocalDate().isBefore(endDate)){
            if(localDateTime.toLocalTime().isAfter(endTime)){
                localDateTime = localDateTime.plusDays(1);
                localDateTime = localDateTime.withHour(startTime.getHour());
                int minute = localDateTime.getMinute();
                if(minute>0&&minute<30){
                    minute=30;
                }
                if(minute>30){
                    minute = 0;
                    localDateTime = localDateTime.withHour(localDateTime.getHour()+1);
                }
                localDateTime = localDateTime.withMinute(minute);
            }
            if(localDateTime.toLocalDate().isAfter(endDate)){
                break;
            }
            Termin termin = new Termin(name, localDateTime, localDateTime.plusMinutes(dauer));
            if(termin.getEnd().toLocalTime().isBefore(endTime)){
                result.add(termin);
            }else {
                localDateTime = localDateTime.plusDays(1);
                continue;
            }
            localDateTime = localDateTime.plusMinutes(30);
        }
        for (Termin termin: termins){
            result.removeIf(termin::inTheTimeFrame);
        }
        return result.toArray(new Termin[0]);
    }

    private static <T> void addItems(ArrayList<T> list, T[] arr){
        if(arr.length>0){
            T iterator = arr[0];
            int index = 1;
            while(iterator!=null&&index<arr.length){
                list.add(iterator);
                iterator = arr[index++];
            }
        }
    }

    private boolean isInTheTimeFrame(LocalDate startInterval, LocalDate endInterval, LocalTime startingHour, LocalTime endingHour,
                                     LocalDateTime meetingStart, LocalDateTime meetingEnd){
        return startInterval.isBefore(meetingStart.toLocalDate())&&endInterval.isAfter(meetingEnd.toLocalDate())&&startingHour.isAfter(meetingStart.toLocalTime())&&endingHour.isAfter(meetingEnd.toLocalTime());
    }
}
