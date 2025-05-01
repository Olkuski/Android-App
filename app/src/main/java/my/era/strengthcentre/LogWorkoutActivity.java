package my.era.strengthcentre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogWorkoutActivity extends AppCompatActivity {
    private EditText etBodyWeight, etBenchPress, etSquat, etDeadlift;
    private Button btnSaveWorkout;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_workout);

        etBodyWeight = findViewById(R.id.etBodyWeight);
        etBenchPress = findViewById(R.id.etBenchPress);
        etSquat = findViewById(R.id.etSquat);
        etDeadlift = findViewById(R.id.etDeadlift);
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout);

        db = AppDatabase.getInstance(this);

        btnSaveWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkout();
            }
        });
    }

    private void saveWorkout() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        float bodyWeight = Float.parseFloat(etBodyWeight.getText().toString());
        float bench = Float.parseFloat(etBenchPress.getText().toString());
        float squat = Float.parseFloat(etSquat.getText().toString());
        float deadlift = Float.parseFloat(etDeadlift.getText().toString());
        float total = bench + squat + deadlift;

        WorkoutLog workout = new WorkoutLog();
        workout.date = date;
        workout.bodyWeight = bodyWeight;
        workout.benchPress = bench;
        workout.squat = squat;
        workout.deadlift = deadlift;
        workout.totalWeight = total;

        db.workoutLogDao().insertWorkout(workout);
        Toast.makeText(this, "Workout Logged!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
