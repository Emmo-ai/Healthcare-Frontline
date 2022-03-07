package classes;

public class AppointmentClass {

    String subject , details, time,date;

    public AppointmentClass() {
    }

    public AppointmentClass(String subject, String details, String time, String date) {
        this.subject = subject;
        this.details = details;
        this.time = time;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
