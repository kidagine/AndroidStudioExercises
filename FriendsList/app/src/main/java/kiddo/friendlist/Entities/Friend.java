package kiddo.friendlist.Entities;

import java.io.Serializable;

public class Friend implements Serializable {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private byte[] profilePicture;


    public Friend(int id, String name, String email, String phoneNumber, byte[] profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
