package dejana.stevanovic.navapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FinanceOffice {

   /* @PrimaryKey(autoGenerate = true)
    private int id;*/

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="DisKz")
    String id;
    @ColumnInfo(name="DisNameLang")
    String name;
    @ColumnInfo(name="DisPlz")
    String zip;
    @ColumnInfo(name="DisOrt")
    String city;
    @ColumnInfo(name="DisStrasse")
    String street;
    @ColumnInfo(name="DisTel")
    String phone;
    @ColumnInfo(name="DisOeffnung")
    String openingHours;
    @ColumnInfo(name="DisFotoUrl")
    String imageUrl;
    @ColumnInfo(name="DisLatitude")
    String longitude;
    @ColumnInfo(name="DisLongitude")
    String latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\n\"DisKz\":").append("\"").append(id).append("\",");
        sb.append("\n\"DisNameLang\":").append("\"").append(name).append("\",");
        sb.append("\n\"DisPlz\":").append("\"").append(zip).append("\",");
        sb.append("\n\"DisOrt\":").append("\"").append(city).append("\",");
        sb.append("\n\"DisStrasse\":").append("\"").append(street).append("\",");
        sb.append("\n\"DisTel\":").append("\"").append(phone).append("\",");
        sb.append("\n\"DisOeffnung\":").append("\"").append(openingHours).append("\",");
        sb.append("\n\"DisFotoUrl\":").append("\"").append(imageUrl).append("\",");
        sb.append("\n\"DisLatitude\":").append("\"").append(latitude).append("\",");
        sb.append("\n\"DisLongitude\":").append("\"").append(longitude).append("\"");
        sb.append("}");
        return sb.toString();
    }
}
