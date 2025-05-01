package my.era.strengthcentre;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "lift_entries")
public class LiftEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int benchPress;
    public int squat;
    public int deadlift;
    public int totalWeight;
    public long date;

    public LiftEntry(int benchPress, int squat, int deadlift, long date) {
        this.benchPress = benchPress;
        this.squat = squat;
        this.deadlift = deadlift;
        this.totalWeight = benchPress + squat + deadlift;
        this.date = date;
    }
}
