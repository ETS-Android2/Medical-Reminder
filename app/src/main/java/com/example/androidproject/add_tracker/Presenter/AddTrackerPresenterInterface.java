package com.example.androidproject.add_tracker.Presenter;

import com.example.androidproject.model.Medicine;
import com.example.androidproject.model.RequestModel;

public interface AddTrackerPresenterInterface {
    public void sendRequest(String id, RequestModel req);
    public void updateStatus(RequestModel req);
    public void getMyFriendMedicines(String id);
    public void deleteHealthTracker(String id);

    void friendList(String friendEmail);
}
