package fr.unice.polytech.si4.isa.devops.teami.api.utils;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;


@Stateless
public class StudentAPI implements IStudentAPI{

    private static final String HOST_KEY = "hyperplanning_host";
    private static final String PORT_KEY = "hyperplanning_port";
    private static final String DEFAULT_HOST = "vps663090.ovh.net";
    private static final String DEFAULT_PORT = "9090";

    private String url;

    private Set<Speciality> specialities;

    public StudentAPI() {
        Map<String, String> env = System.getenv();
        String host = env.get(HOST_KEY);
        String port = env.get(PORT_KEY);
        if (host == null) host = DEFAULT_HOST;
        if (port == null) port = DEFAULT_PORT;
        this.url = "http://" + host + ":" + port;
    }

    private String readAll(BufferedReader rd) {
        StringBuilder res = new StringBuilder();

        try {
            for (String cur = rd.readLine(); cur != null && cur.length() > 0; cur = rd.readLine()) {
                res.append(cur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res.toString();
    }

    public List<Student> getGraduatedStudent(int year){
        
        try {
            InputStream is = new URL(url + "/getgraduated/" + year).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            is.close();
            JSONArray jsonArray = new JSONArray(jsonText);
            return parseJsonArray(jsonArray);
        } catch (JSONException | IOException e) {
            return null;
        }
    }

    private List<Student> parseJsonArray(JSONArray jsonArray) {
        List<Student> studentList = new ArrayList<>();
        specialities = new HashSet<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Speciality speciality = null;
                for (Speciality spe : specialities) {
                    if (spe.getName().equals(jsonObject.getString("speciality"))) {
                        speciality = spe;
                    }
                }

                if (speciality == null) {
                    speciality = new Speciality(jsonObject.getString("speciality"));
                    specialities.add(speciality);
                }

                studentList.add(
                        new Student(
                                jsonObject.getString("firstName"),
                                jsonObject.getString("lastName"),
                                jsonObject.getString("email"),
                                jsonObject.getInt("graduatingYear"),
                                speciality,
                                i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return studentList;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }
}
