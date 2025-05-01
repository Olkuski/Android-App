package my.era.strengthcentre;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout_log")
public class WorkoutLog {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public float bodyWeight;
    public float benchPress;
    public float squat;
    public float deadlift;
    public float totalWeight;
}
