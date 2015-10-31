package testTask;

import org.json.JSONException;

public class Main {
    public static void main(String[] args) {
        try {
            new TestTask().solve();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
