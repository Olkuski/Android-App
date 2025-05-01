package my.era.strengthcentre;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProgressActivity extends AppCompatActivity {
    private LineChart lineChart;
    private AppDatabase db;
    private List<String> dateLabels; // Store formatted dates for the X-axis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        lineChart = findViewById(R.id.lineChart);
        db = AppDatabase.getInstance(this);

        setupChart();
    }

    private void setupChart() {
        List<WorkoutLog> workouts = db.workoutLogDao().getAllWorkouts();
        dateLabels = new ArrayList<>();

        ArrayList<Entry> benchEntries = new ArrayList<>();
        ArrayList<Entry> squatEntries = new ArrayList<>();
        ArrayList<Entry> deadliftEntries = new ArrayList<>();
        ArrayList<Entry> totalEntries = new ArrayList<>();

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM", Locale.getDefault()); // e.g., "03 Apr"

        for (int i = 0; i < workouts.size(); i++) {
            WorkoutLog workout = workouts.get(i);
            benchEntries.add(new Entry(i, workout.benchPress));
            squatEntries.add(new Entry(i, workout.squat));
            deadliftEntries.add(new Entry(i, workout.deadlift));
            totalEntries.add(new Entry(i, workout.totalWeight));

            // Convert date format
            try {
                String formattedDate = outputFormat.format(inputFormat.parse(workout.date));
                dateLabels.add(formattedDate);
            } catch (Exception e) {
                dateLabels.add("N/A");
            }
        }

        LineDataSet benchSet = new LineDataSet(benchEntries, "Bench Press");
        benchSet.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        benchSet.setCircleColor(getResources().getColor(android.R.color.holo_blue_dark));

        LineDataSet squatSet = new LineDataSet(squatEntries, "Squat");
        squatSet.setColor(getResources().getColor(android.R.color.holo_red_dark));
        squatSet.setCircleColor(getResources().getColor(android.R.color.holo_red_dark));

        LineDataSet deadliftSet = new LineDataSet(deadliftEntries, "Deadlift");
        deadliftSet.setColor(getResources().getColor(android.R.color.holo_green_dark));
        deadliftSet.setCircleColor(getResources().getColor(android.R.color.holo_green_dark));

        LineDataSet totalSet = new LineDataSet(totalEntries, "Total Lift");
        totalSet.setColor(getResources().getColor(android.R.color.black));
        totalSet.setCircleColor(getResources().getColor(android.R.color.black));

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(benchSet);
        dataSets.add(squatSet);
        dataSets.add(deadliftSet);
        dataSets.add(totalSet);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);

        // Format the X-axis to show dates
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new DateValueFormatter(dateLabels));
        xAxis.setLabelRotationAngle(-45f); // Rotate 45 degrees
        xAxis.setDrawGridLines(false);

        lineChart.invalidate(); // Refresh chart
    }

    // Custom formatter to show formatted dates on X-axis
    private static class DateValueFormatter extends ValueFormatter {
        private final List<String> labels;

        DateValueFormatter(List<String> labels) {
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value) {
            int index = (int) value;
            if (index >= 0 && index < labels.size()) {
                return labels.get(index);
            }
            return "";
        }
    }
}
