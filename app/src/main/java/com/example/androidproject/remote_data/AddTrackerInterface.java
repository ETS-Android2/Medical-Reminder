package com.example.androidproject.remote_data;

import com.example.androidproject.model.RequestModel;

import java.util.ArrayList;

public interface AddTrackerInterface {
    public void sendRequest(String id,RequestModel req);
    public void reqList(String id);
    public ArrayList<RequestModel> returnRequestArr();
    public void updateStatus(String id);
    public void friendList(String id);
    public ArrayList<RequestModel> returnFriendsArr();
    public void test(String id);

}
