package com.mam.morphtransitions.sample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.mam.morphtransitions.FabTransform;
import com.mam.morphtransitions.MorphTransform;
/**
 * *
 * *          ____  ____ _____ ___   ____
 * *         | \ \ / / |/ _  || \ \ / / |
 * *         | |\ V /| | (_| || |\ V /| |
 * *         |_| \_/ |_|\__,_||_| \_/ |_|
 * *
 * Created by Mohammad Ali Mirshahbazi
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        final Switch switchFullScreen = (Switch) findViewById(R.id.switch_full_screen);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                if (switchFullScreen.isChecked()) {
                    intent = new Intent(MainActivity.this, FullScreenActivity.class);
                } else {
                    intent = DialogActivity.newIntent(MainActivity.this, DialogActivity.TYPE_FAB);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    FabTransform.addExtras(intent, ContextCompat.getColor(MainActivity.this, R.color.colorAccent),
                            R.drawable.ic_mood_24dp);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                            (MainActivity.this, view, getString(R.string.transition_morph));
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.do_nothing);
                }
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DialogActivity.newIntent(MainActivity.this, DialogActivity.TYPE_BUTTON);
                if (Build.VERSION.SDK_INT >= 21) {
                    MorphTransform.addExtras(intent,
                            ContextCompat.getColor(MainActivity.this, R.color.colorAccent),
                            getResources().getDimensionPixelSize(R.dimen.dialog_corners));
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v,
                                    getString(R.string.transition_morph));
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.do_nothing);
                }
            }
        });
    }
}
