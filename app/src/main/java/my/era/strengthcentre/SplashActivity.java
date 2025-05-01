package my.era.strengthcentre;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo = findViewById(R.id.ivLogo);
        tvText = findViewById(R.id.tvText);

        // Load animations
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        Animation textAnim = AnimationUtils.loadAnimation(this, R.anim.text_animation);

        // Apply animations to views
        ivLogo.startAnimation(logoAnim);
        tvText.startAnimation(textAnim);

        // Transition to MainActivity after 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // Delay of 3 seconds
    }
}
