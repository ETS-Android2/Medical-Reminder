package com.example.androidproject.remote_data;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.RequestModel;

import java.util.ArrayList;

public interface AddTrackerInterface {
    public void sendRequest(String id,RequestModel req);
    public void reqList(String id);
    public ArrayList<RequestModel> returnRequestArr();
    public void updateStatus(String id);
    public ArrayList<Medicine> friendList(String id);
    public ArrayList<RequestModel> returnFriendsArr();
    public void getMyFriendMedicines(String id);

}
