package dejana.stevanovic.navapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Convertor{

    public static FinanceOffice fromJsonObjectToFinanceOffice(JSONObject office){

        FinanceOffice officeModel = new FinanceOffice();
        // Get the current office (json object) data
        try {
            officeModel.id = office.getString("DisKz");

        officeModel.name = office.getString("DisNameLang");
        officeModel.zip = office.getString("DisPlz");
        officeModel.city = office.getString("DisOrt");
        officeModel.street = office.getString("DisStrasse");
        officeModel.phone = office.getString("DisTel");
        officeModel.openingHours = office.getString("DisOeffnung");
        officeModel.imageUrl = office.getString("DisFotoUrl");
        officeModel.latitude = office.getString("DisLatitude");
        officeModel.longitude = office.getString("DisLongitude");

        } catch (JSONException e) {
            e.printStackTrace();
            officeModel = null;
        }

        return officeModel;
    }

    public static FinanceOffice fromStringToFinanceOffice(String jsonString){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return fromJsonObjectToFinanceOffice(jsonObject);
    }
}