package classes;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    private long id;
    private String pet;
    private Date appointmentDate;
    private String veterinarianName;
    private String reason;
    private String photoBase64;
    private boolean isCompleted;

    public Appointment(String pet, Date appointmentDate, String veterinarianName, String reason) {
        this.pet = pet;
        this.appointmentDate = appointmentDate;
        this.veterinarianName = veterinarianName;
        this.reason = reason;
        this.isCompleted = false;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getVeterinarianName() {
        return veterinarianName;
    }

    public void setVeterinarianName(String veterinarianName) {
        this.veterinarianName = veterinarianName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", pet='" + pet + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", veterinarianName='" + veterinarianName + '\'' +
                ", reason='" + reason + '\'' +
                ", photoBase64='" + photoBase64 + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}