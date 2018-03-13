package com.code.challenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class JSONReaderTest {

    @Test
    public void testWrongFilePath()  throws JSONException, IOException {

        JSONHandler json = new JSONHandler();
        JSONArray jsonArray = json.readJSONFile("src/main/resources/payment.json");

        Assert.assertNotNull(jsonArray);
        Assert.assertThat(jsonArray.length(), is(0));

    }

    @Test
    public void testValidFileReturnsJSONArray()  throws JSONException, IOException {

        JSONHandler json = new JSONHandler();
        JSONArray jsonArray = json.readJSONFile("src/main/resources/payments-in.json");

        Assert.assertNotNull(jsonArray);
        Assert.assertThat(jsonArray.length(), is(4));
    }

    @Test
    public void jsonReaderReadsFiles()  throws JSONException, IOException {

        String filePath = "src/main/resources/payments-in.json";

        JSONHandler json = new JSONHandler();
        JSONArray jsonArray = json.readJSONFile(filePath);
        List <Payment> payments = json.calculatePayments(jsonArray);

        Assert.assertNotNull(payments);
        Assert.assertThat(payments.size(), is(4));
    }
}
