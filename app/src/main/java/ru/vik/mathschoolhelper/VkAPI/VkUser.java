package ru.vik.mathschoolhelper.VkAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VkUser{
    @SerializedName("photo_200")
    String photoUrl;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("bdate")
    String birthDate;
    @SerializedName("city")
    City city = new City();
    @SerializedName("phone")
    String mobileNumber;
    @SerializedName("screen_name")
    String nickname;

    VkUser(){}

    public String getPhotoUrl() {
        return photoUrl;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public City getCity() {
        return city;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public String getNickname() {
        return nickname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public class City{
        @SerializedName("id")
        Integer id;
        @SerializedName("title")
        String title;

        City(){}

        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
    }
}