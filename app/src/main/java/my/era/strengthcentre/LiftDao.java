package my.era.strengthcentre;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface LiftDao {
    @Insert
    void insertLiftEntry(LiftEntry liftEntry);

    @Query("SELECT * FROM lift_entries ORDER BY date ASC")
    List<LiftEntry> getAllEntries();
}
