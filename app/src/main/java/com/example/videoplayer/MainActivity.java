package com.sriyanksiddhartha.speechtotext;

import android.content.Intent;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private TextView txvResult;
    private EditText text_input;
    private Button get_text_input;
    private VideoView output_video;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txvResult = (TextView) findViewById(R.id.txvResult);
		text_input = (EditText) findViewById(R.id.Input_text);
		get_text_input = (Button) findViewById(R.id.Input_text_done);
		get_text_input.setOnClickListener( this);
		output_video = (VideoView) findViewById(R.id.videoView);
	}
public void getTextInput(){

		String input = text_input.getText().toString().trim();
	if(TextUtils.isEmpty(input)){
		Toast.makeText(this,"Enter email", Toast.LENGTH_SHORT).show();
		return;
	}

}
	public void onClick(View v) {
		if(v==get_text_input)
		{
			txvResult.setText(text_input.getText().toString().trim());
		}
		String inputt = text_input.getText().toString().trim();
		String tokens[] = inputt.split(" ");

		//String videopath = String.format("android.resource://com.sriyanksiddhartha.speechtotext/R.raw.%s",input);

		//String vid="android.resource://com.sriyanksiddhartha.speechtotext/"+R.raw.baby;
		//System.out.println(videopath);
		//System.out.println(vid);

int i=0;

        for(String input : tokens) {
        	System.out.print("here");
			//  String str = "countdown"; //Suppose input word is "countdown"
			int videoResource = getResources().getIdentifier(input, "raw", getPackageName()); //calculating id for that video name
			String path = "android.resource://" + getPackageName() + "/" + videoResource; //using the id found above for the required video
			Uri uri = Uri.parse(path);
			output_video.setVideoURI(uri);
			output_video.start();
            output_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mediaPlayer) {
					Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();

				}
			});



		}

		}
	public void getSpeechInput(View view) {

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intent, 10);
		} else {
			Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case 10:
				if (resultCode == RESULT_OK && data != null) {
					ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					txvResult.setText(result.get(0));
				}
				break;
		}
	}
}
