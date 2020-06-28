package de.fh_kiel.oop.services.TwitchService;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TwitchTwitchApiRequestInterface implements TwitchApiRequestInterface {
    private final String apiUrl;

    public TwitchTwitchApiRequestInterface(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String getStreams(String streamName) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(apiUrl + "?user_login=" + streamName);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer q2nm5jqm1nubngxxiom65ede4ms55h");
            con.setRequestProperty("Client-ID", "jm7g58m1gg888syah7ffy1ctmrjooc");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoOutput(true);

            int status = con.getResponseCode();
            System.err.println("Status: " + status);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);

            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
