package com.example.user1.storyapp.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user1.storyapp.R;
import com.example.user1.storyapp.model.Page;
import com.example.user1.storyapp.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {
    // 2nd Activity
    private String name;
    public static final String TAG = StoryActivity.class.getSimpleName();
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private Story story;
    //declare the stack
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_story);
        storyImageView = (ImageView)findViewById(R.id.storyimageView);
        storyTextView = (TextView)findViewById(R.id.storyTextView);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        choice2Button = (Button)findViewById(R.id.choice2Button);


        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        if (name == null || name.isEmpty()){
            name = "Friend";
        }

        Log.d(TAG , name);
        story = new Story();
        loadPage(0);
    }

    //IT LOAD THE PAGE IMAGE,STRING,BUTTON
    private void loadPage(int pageNumber) {
       //adding item on to a stack is called pushing into the stack
        pageStack.push(pageNumber);// pages are added into the stack; stack will be build up as we go through it.
        // A perticular page is displayed by getPage() from Story.java ;
        final Page page = story.getPage(pageNumber);

        // Image is loaded
        Drawable image = ContextCompat.getDrawable(this,page.getImageId());
        storyImageView.setImageDrawable(image);

        //Text is loaded
        String pageText = getString(page.getTextId());
        //Add name if placeholder included.Won't add if not
        pageText = String.format(pageText,name);
        storyTextView.setText(pageText);
        if(page.isFinalPage()){
          choice1Button.setVisibility(View.INVISIBLE);
          choice2Button.setText(R.string.play_again_button_text);//we can extract code to string resource by press alt+enter on the yellow marked string the press extract string resource add a string with undersore for space
          choice2Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                    //finish();
                  loadPage(0);
              }
          });

        }
        else {
            //converting everything to a Fn refactor>extract>Methord  then type in the fn name ;
            loadButtons(page);


        }
    }

    private void loadButtons(final Page page) {

        choice1Button.setVisibility(View.VISIBLE);
        //choice1 and choice2 buttons are loaded
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load the next page in the same activity
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load the next page in the same activity
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
       pageStack.pop();
       if (pageStack.isEmpty()) {
           super.onBackPressed();
       }
       else {
           loadPage(pageStack.pop());
       }
    }
}







