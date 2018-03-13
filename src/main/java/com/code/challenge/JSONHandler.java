package com.code.challenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {

    public JSONArray readJSONFile(String filePath) throws JSONException, IOException {
        if (Files.exists(Paths.get(filePath))){
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            return new JSONArray(content);
        }
        return new JSONArray();
    }

    public List <Payment> calculatePayments(JSONArray jsonArray) throws JSONException {

        List<Payment> paymentList = new ArrayList <>();

        for (int i =0 ; i < jsonArray.length();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            paymentList.add(new Payment(
                    jsonObject.getString("receiverCountry"),
                    jsonObject.getString("receiverAccountRef"),
                    jsonObject.getString("senderAccountRef"),
                    jsonObject.getString("sendersPaymentReference"),
                    jsonObject.getString("valueDate"),
                    jsonObject.getString("currency"),
                    jsonObject.getString("amount"),
                    jsonObject.has("messageIdentifier") ? jsonObject.getString("messageIdentifier") : "")
            );
        }

        return paymentList;
    }

}
