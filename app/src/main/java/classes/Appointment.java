package classes;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class Appointment implements Parcelable {
    private long id;
    private Pet pet;
    private Date appointmentDate;
    private String veterinarianName;
    private String reason;
    private String photoBase64;  // Store photo as Base64 string
    private boolean isCompleted;

    public Appointment(Pet pet, Date appointmentDate, String veterinarianName, String reason) {
        this.pet = pet;
        this.appointmentDate = appointmentDate;
        this.veterinarianName = veterinarianName;
        this.reason = reason;
        this.isCompleted = false;
    }

    // Constructor with photo
    public Appointment(Pet pet, Date appointmentDate, String veterinarianName,
                       String reason, Bitmap photo) {
        this(pet, appointmentDate, veterinarianName, reason);
        setPhoto(photo);
    }

    // Photo handling methods
    public void setPhoto(Bitmap photo) {
        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] photoBytes = stream.toByteArray();
            this.photoBase64 = Base64.encodeToString(photoBytes, Base64.DEFAULT);
        }
    }

    public Bitmap getPhoto() {
        if (photoBase64 != null) {
            byte[] photoBytes = Base64.decode(photoBase64, Base64.DEFAULT);
            return android.graphics.BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Parcelable implementation
    protected Appointment(Parcel in) {
        id = in.readLong();
        pet = in.readParcelable(Pet.class.getClassLoader());
        appointmentDate = new Date(in.readLong());
        veterinarianName = in.readString();
        reason = in.readString();
        photoBase64 = in.readString();
        isCompleted = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable((Parcelable) pet, flags);
        dest.writeLong(appointmentDate.getTime());
        dest.writeString(veterinarianName);
        dest.writeString(reason);
        dest.writeString(photoBase64);
        dest.writeByte((byte) (isCompleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    @Override
    public String toString() {
        return "Appointment{" +
                "pet=" + pet.getName() +
                ", date=" + appointmentDate +
                ", veterinarian='" + veterinarianName + '\'' +
                ", reason='" + reason + '\'' +
                ", completed=" + isCompleted + '}';
    }
}