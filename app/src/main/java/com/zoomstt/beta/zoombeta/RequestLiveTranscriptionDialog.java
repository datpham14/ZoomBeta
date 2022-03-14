package com.zoomstt.beta.zoombeta;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zoomstt.beta.R;

import us.zoom.sdk.InMeetingService;
import us.zoom.sdk.ZoomSDK;

public class RequestLiveTranscriptionDialog extends Dialog implements View.OnClickListener {
    public RequestLiveTranscriptionDialog(@NonNull Context context) {
        super(context);
    }

    public RequestLiveTranscriptionDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RequestLiveTranscriptionDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static void show(MyMeetingActivity myMeeting) {

        RequestLiveTranscriptionDialog dialog = new RequestLiveTranscriptionDialog(myMeeting);
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_request_live_transcription);
        findViewById(R.id.requestBtn).setOnClickListener(this);
        findViewById(R.id.anonymouslyRequestBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InMeetingService inMeetingService = ZoomSDK.getInstance().getInMeetingService();
        if (v.getId() == R.id.requestBtn) {
            if (inMeetingService != null) {
                inMeetingService.getInMeetingLiveTranscriptionController().requestToStartLiveTranscription(false);
            }
        } else if (v.getId() == R.id.anonymouslyRequestBtn) {
            if (inMeetingService != null) {
                inMeetingService.getInMeetingLiveTranscriptionController().requestToStartLiveTranscription(true);
            }
        }
        dismiss();
    }
}
