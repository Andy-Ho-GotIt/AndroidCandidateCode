package alex.example.gotit.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/12/2016.
 */
public class FeedParser {
    public static List<Feed> parse(JSONArray response) {
        int length = response.length();
        Log.d("vuong.hong", "got " + length + " results");

        List<Feed> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jsonObject = response.getJSONObject(i);

                int qId = jsonObject.getInt("qid");
                String title = jsonObject.getString("title");
                int tid = jsonObject.getInt("tid");
                int processingStatus = jsonObject.getInt("processing_status");

                JSONObject processingData = jsonObject.getJSONObject("processing_data");
                String authorName = processingData.getString("name");

                long created = jsonObject.getLong("created");
                long updated = jsonObject.getLong("updated");

                JSONObject author = jsonObject.getJSONObject("author");
                String studentAvatarUrl = author.getString("avatar");

                // get only first attachment
                JSONArray attachments = jsonObject.getJSONArray("attachments");
                int attachmentsLength = attachments.length();
                String url;
                if (attachmentsLength <= 0) url = "";
                else url = attachments.getJSONObject(0).getString("url");

                Feed feedItem = new MathFeed(processingStatus == 5 ? Status.EXPLAINED : Status.IN_PROGRESS, url, authorName, created, studentAvatarUrl);

                if (i == 0) Log.d("vuong.hong", feedItem.toString());

                Log.d("vuong.hong", "parsed item");

                result.add(feedItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
