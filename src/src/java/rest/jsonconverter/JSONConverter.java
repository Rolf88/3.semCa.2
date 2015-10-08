package rest.jsonconverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.List;
import rest.models.ContactInfoPerson;

public class JSONConverter {

    public static String PersonToJSON(Person person) {
        return new Gson().toJson(PersonToJsonObject(person));
    }

    public static String PersonToJSON(List<Person> persons) {
        JsonArray personsArr = new JsonArray();
        for (Person person : persons) {
            personsArr.add(PersonToJsonObject(person));
        }

        return new Gson().toJson(personsArr);
    }
    
    public static String ContactInfoPersonToJSON(ContactInfoPerson person) {
        return new Gson().toJson(ContactInfoPersonToJsonObject(person));
    }

    public static String ContactInfoPersonToJSON(List<ContactInfoPerson> persons) {
        JsonArray personsArr = new JsonArray();
        for (ContactInfoPerson person : persons) {
            personsArr.add(ContactInfoPersonToJsonObject(person));
        }

        return new Gson().toJson(personsArr);
    }
    

    private static JsonObject PersonToJsonObject(Person person) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", person.getId());
        obj.addProperty("firstName", person.getFirstName());
        obj.addProperty("lastName", person.getLastName());
        obj.addProperty("email", person.getEmail());

        JsonArray hobs = new JsonArray();
        JsonArray phones = new JsonArray();

        for (Hobby hobby : person.getHobbies()) {
            JsonObject hob = new JsonObject();
            hob.addProperty("id", hobby.getId());
            hob.addProperty("name", hobby.getName());
            hob.addProperty("description", hobby.getDescription());
            hobs.add(hob);
        }

        for (Phone phone : person.getPhones()) {
            JsonObject ph = new JsonObject();
            ph.addProperty("id", phone.getId());
            ph.addProperty("number", phone.getNumber());
            ph.addProperty("description", phone.getDescription());
            phones.add(ph);
        }

        obj.add("hobbies", hobs);
        obj.add("phones", phones);
        return obj;
    }

    private static JsonObject ContactInfoPersonToJsonObject(ContactInfoPerson person) {
        JsonObject obj = new JsonObject();
        obj.addProperty("firstName", person.getFirstName());
        obj.addProperty("lastName", person.getLastName());
        obj.addProperty("email", person.getEmail());
        
        JsonArray phones = new JsonArray();

        for (Phone phone : person.getPhones()) {
            JsonObject ph = new JsonObject();
            ph.addProperty("id", phone.getId());
            ph.addProperty("number", phone.getNumber());
            ph.addProperty("description", phone.getDescription());
            phones.add(ph);
        }
        
        obj.add("phones", phones);
        return obj;
    }
}
