import com.mavi.restrailwaysecurity.RestRailwaySecurityApplication;
import com.mavi.restrailwaysecurity.conroller.StationTrackController;
import com.mavi.restrailwaysecurity.entity.StationModel;
import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @InjectMocks
    private StationTrackController stationTrackController;

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
    public void testGetAllStationTracks() {
        // Create a mock StationModel entity
        StationModel stationModel = new StationModel();
        stationModel.setId(1L);
        stationModel.setName("Test Station");

        // Create some mock StationTrack entities
        StationTrack stationTrack1 = new StationTrack(1L, "Track 1", stationModel, new HashSet<>());
        StationTrack stationTrack2 = new StationTrack(2L, "Track 2", stationModel, new HashSet<>());
        List<StationTrack> expectedStationTracks = Arrays.asList(stationTrack1, stationTrack2);

        // Mock the behavior of the StationTrackRepository
        Mockito.when(stationTrackRepository.findAll()).thenReturn(expectedStationTracks);

        // Call the getAllStationTracks method on the controller
        List<StationTrack> actualStationTracks = stationTrackController.getAllStationTracks();

        // Verify that the returned list contains the expected StationTrack entities
        assertEquals(expectedStationTracks.size(), actualStationTracks.size());
        assertTrue(expectedStationTracks.containsAll(actualStationTracks));
        assertTrue(actualStationTracks.containsAll(expectedStationTracks));
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
