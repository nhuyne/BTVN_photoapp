package com.example.btvn_buoi6;

import java.util.ArrayList;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotoData {
   /* public static ArrayList<Photo> generatePhotoData(){
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo(0,"https://i.pinimg.com/564x/ee/43/bf/ee43bf7911c3c8ae4f6005a2fd270e37.jpg","Instagram","Instagram (thường được viết tắt là IG hoặc Insta) là một dịch vụ mạng xã hội chia sẻ hình ảnh và video của Mỹ được tạo ra bởi Kevin Systrom và Mike Krieger. Vào tháng 4 năm 2012, Facebook (nay là Meta) đã mua lại dịch vụ này với giá khoảng 1 tỷ đô la Mỹ bằng tiền mặt và cổ phiếu."));
        photos.add(new Photo(1,"https://i.pinimg.com/564x/e3/0e/a1/e30ea1834672b38269b337390d76e535.jpg","Facebook","Facebook là một phương tiện truyền thông xã hội và dịch vụ mạng xã hội trực tuyến thành lập vào năm 2004 của Mỹ thuộc sở hữu của Meta Platforms có trụ sở tại Menlo Park, California. Nó được Mark Zuckerberg, cùng với các sinh viên Đại học Harvard và bạn cùng phòng là Eduardo Saverin, Andrew McCollum, Dustin Moskovitz, Chris Hughes sáng lập. Đây được coi là một trong những công ty công nghệ Big Four cùng với Amazon, Apple và Google.[7][8] "));
        photos.add(new Photo(2,"https://i.pinimg.com/564x/f3/51/8f/f3518fe9a5266a5217d542cb890fbe80.jpg","Chrome","Google Chrome[14] là một trình duyệt web miễn phí, được phát triển bởi Google, sử dụng nền tảng V8 engine. Dự án mã nguồn mở đứng sau Google Chrome được biết với tên gọi Chromium."));
        photos.add(new Photo(3,"https://i.pinimg.com/564x/0e/cc/7f/0ecc7f63948c574059ed1b2e59ae92e9.jpg","Tiktok","TikTok là nền tảng video âm nhạc và mạng xã hội của Trung Quốc [2] được ra mắt vào năm 2017, dành cho các thị trường bên ngoài Trung Quốc.[3] bởi Trương Nhất Minh, người sáng lập của ByteDance.[4]"));
        photos.add(new Photo(4,"https://i.pinimg.com/564x/b0/ad/e8/b0ade842615f62c0514083530a284cd9.jpg","Discord","Discord là một phần mềm miễn phí về phân phối kỹ thuật số, nhắn tin tức thời và VoIP.[10] Người dùng có thể giao tiếp bằng các cuộc gọi thoại, cuộc gọi video, tin nhắn văn bản, các phương tiện và tệp trong các cuộc trò chuyện riêng tư hoặc như một phần của cộng đồng được gọi là \"máy chủ\" (server).[a"));
        photos.add(new Photo(5,"https://i.pinimg.com/564x/d4/74/c7/d474c7194e4a617b0c9d4d714a151098.jpg","App Store","App Store là nền tảng phân phối kỹ thuật số các ứng dụng, hay Chợ ứng dụng cho các thiết bị chạy hệ điều hành iOS, phát triển và duy trì bởi Apple Inc. Cửa hàng App Store cho phép người dùng tìm kiếm, tải xuống cũng như đánh giá các ứng dụng được phát triển bằng bộ phát triển phần mềm iOS và iPadOS của Apple."));
        return photos;
    }
    public static Photo getPhotoFromId(int id){
        ArrayList<Photo> phs = generatePhotoData();
        for (int i = 0; i <phs.size(); i++){
            if(phs.get(i).getId() == id){
                return phs.get(i);
            }
        }
        return null;
    }*/
   private static Context context;

    public static void init(Context context) {
        PhotoData.context = context;
    }

    public static ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset("photo.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source_photo");
                String title = jsonObject.getString("title_photo");
                String description = jsonObject.getString("description_photo");
                photos.add(new Photo(id, source, title, description));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photos;
    }

    private static String loadJSONFromAsset(String fileName) throws IOException {
        String jsonString;
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        jsonString = new String(buffer, "UTF-8");
        return jsonString;
    }
    public static Photo getPhotoFromId(int id) {
        Photo photo = null;

        try {
            String jsonString = loadJSONFromAsset("photo.json"); // Assuming the JSON file is named photos.json and is located in the assets folder
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int photoId = jsonObject.getInt("id");

                if (photoId == id) {
                    String source = jsonObject.getString("source_photo");
                    String title = jsonObject.getString("title_photo");
                    String description = jsonObject.getString("description_photo");
                    photo = new Photo(id, source, title, description);
                    break;
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photo;
    }
}
