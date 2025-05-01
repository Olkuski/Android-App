package my.era.strengthcentre;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface WorkoutLogDao {
    @Insert
    void insertWorkout(WorkoutLog workout);

    @Query("SELECT * FROM workout_log ORDER BY date DESC")
    List<WorkoutLog> getAllWorkouts();
}
