import com.mavi.restrailwaysecurity.RestRailwaySecurityApplication;
import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = RestRailwaySecurityApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class StationTrackControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StationTrackRepository stationTrackRepository;

    @Test
    public void testGetStationTrackById() throws Exception {
        StationTrack stationTrack = new StationTrack();
        stationTrack.setId(1L);
        stationTrack.setName("Test StationTrack");
        Optional<StationTrack> optionalStationTrack = Optional.of(stationTrack);
        when(stationTrackRepository.findById(1L)).thenReturn(optionalStationTrack);
        mockMvc.perform(MockMvcRequestBuilders.get("/station-tracks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test StationTrack"));
    }

    @Test
    public void testCreateStationTrack() throws Exception {
        StationTrack stationTrack = new StationTrack();
        stationTrack.setId(1L);
        stationTrack.setName("Test StationTrack");
        when(stationTrackRepository.save(any())).thenReturn(stationTrack);
        mockMvc.perform(MockMvcRequestBuilders.post("/station-tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test StationTrack\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test StationTrack"));
    }

    @Test
    public void testUpdateStationTrack() throws Exception {
        StationTrack stationTrack = new StationTrack();
        stationTrack.setId(1L);
        stationTrack.setName("Test StationTrack");
        Optional<StationTrack> optionalStationTrack = Optional.of(stationTrack);
        when(stationTrackRepository.findById(1L)).thenReturn(optionalStationTrack);
        when(stationTrackRepository.save(any())).thenReturn(stationTrack);
        mockMvc.perform(MockMvcRequestBuilders.put("/station-tracks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated StationTrack\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated StationTrack"));
    }

    @Test
    public void testDeleteStationTrack() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/station-tracks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteAllStationTracks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/station-tracks"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
