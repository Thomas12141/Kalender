import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Termin {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private String name;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Termin(String name, LocalDateTime start, LocalDateTime end){
        if(name==null||start==null||end==null){
            throw new IllegalArgumentException("Cant initialize the next meeting: " + this);
        }
        if(start.isAfter(end)){
            throw new IllegalArgumentException("The start time is before the end time: " + this);
        }
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return (int) MINUTES.between(start, end);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean inTheTimeFrame(Termin termin){
        if(termin.start.isEqual(this.start)||termin.end.isEqual(this.end)){
            return true;
        }
        if(this.start.isAfter(termin.start)&&this.start.isBefore(end)||this.end.isAfter(termin.start)&&this.end.isBefore(termin.end)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "\tName: " + name + "\n\t\t" + String.format("start date: %s\t\tend date: %s", start.format(dateTimeFormatter), end.format(dateTimeFormatter));
    }
}
