package com.example.androidproject.remote_data;

import com.example.androidproject.model.Medicine;
import com.google.android.gms.tasks.Task;

public interface RemoteSource {
    public Task<Void> add(Medicine med);
}
