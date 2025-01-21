package com.example.chatapplication.view.fragments;

import com.example.chatapplication.services.notifications.MyResponse;
import com.example.chatapplication.services.notifications.Sender;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {

    // Method for sending notifications
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAYJMn-B4:APA91bHKbZl0nBCuT40o9JEY_hGDWwoLP46rb-0nlfSzXdH-ppWLtiX3EUEL4_LL6Ov7GV8yB7OInsT75ZLCZG18S3NfI2nJxd-z2gHCK5CkXEs3T7gMWP9vJjp2oa2XkQ9y8CZb92e9"
    })
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);

    // Method for uploading files

    @Multipart
    @POST("upload") // Corrected endpoint
    Call<ResponseBody> uploadFile(
            @Part MultipartBody.Part file,
            @Part("name") RequestBody name
    );
}