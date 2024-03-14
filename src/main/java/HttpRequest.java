import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class HttpRequest {
    public static void main(String[] args) throws IOException {
        String key = System.getenv("KEY");
        String type = "json";
        String service = "bikeStationMaster";

        StringJoiner requestUrl = new StringJoiner("/");
        requestUrl.add("http://openapi.seoul.go.kr:8088")
                .add(key)
                .add(type)
                .add(service)
                .add("1")
                .add("10");

        URL url = new URL(requestUrl.toString());

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoInput(true);
        con.setUseCaches(false);

        con.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> bikeStationId = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            JsonNode rootNode = mapper.readTree(line).get("bikeStationMaster");
            ArrayNode rows = (ArrayNode) rootNode.get("row");
            for (JsonNode row : rows) {
                bikeStationId.put(row.get("LENDPLACE_ID").asText(), row.get("STATN_ADDR1").asText());
            }
        }

        System.out.println(bikeStationId);
    }
}
