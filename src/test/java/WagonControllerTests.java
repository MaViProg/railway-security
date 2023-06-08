import com.mavi.restrailwaysecurity.RestRailwaySecurityApplication;
import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.repository.WagonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RestRailwaySecurityApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class WagonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WagonRepository wagonRepository;

    @Test
    public void testGetAllWagons() throws Exception {
        Wagon wagon1 = new Wagon();
        wagon1.setId(1L);
        wagon1.setNumber("Wagon 1");
        wagon1.setType("Type 1");
        wagon1.setTareWeight(10.0);
        wagon1.setLoadCapacity(20.0);

        Wagon wagon2 = new Wagon();
        wagon2.setId(2L);
        wagon2.setNumber("Wagon 2");
        wagon2.setType("Type 2");
        wagon2.setTareWeight(15.0);
        wagon2.setLoadCapacity(25.0);

        List<Wagon> wagons = Arrays.asList(wagon1, wagon2);

        given(wagonRepository.findAll()).willReturn(wagons);

        mockMvc.perform(get("/api/wagons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].number", is(wagon1.getNumber())))
                .andExpect(jsonPath("$[1].number", is(wagon2.getNumber())));
    }

    @Test
    public void testGetWagonById() throws Exception {
        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setNumber("Wagon 1");
        wagon.setType("Type 1");
        wagon.setTareWeight(10.0);
        wagon.setLoadCapacity(20.0);

        given(wagonRepository.findById(1L)).willReturn(Optional.of(wagon));

        mockMvc.perform(get("/api/wagons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(wagon.getId().intValue())))
                .andExpect(jsonPath("$.number", is(wagon.getNumber())))
                .andExpect(jsonPath("$.type", is(wagon.getType())))
                .andExpect(jsonPath("$.tareWeight", is(wagon.getTareWeight())))
                .andExpect(jsonPath("$.loadCapacity", is(wagon.getLoadCapacity())));
    }

    @Test
    public void testCreateWagon() throws Exception {
        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setNumber("Wagon 1");
        wagon.setType("Type 1");
        wagon.setTareWeight(10.0);
        wagon.setLoadCapacity(20.0);

        given(wagonRepository.save(any(Wagon.class))).willReturn(wagon);

        mockMvc.perform(post("/api/wagons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"number\": \"Wagon 1\", \"type\": \"Type 1\", \"tareWeight\": 10.0, \"loadCapacity\": 20.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(wagon.getId().intValue())))
                .andExpect(jsonPath("$.number", is(wagon.getNumber())))
                .andExpect(jsonPath("$.type", is(wagon.getType())))
                .andExpect(jsonPath("$.tareWeight", is(wagon.getTareWeight())))
                .andExpect(jsonPath("$.loadCapacity", is(wagon.getLoadCapacity())));
    }

    @Test
    public void testUpdateWagon() throws Exception {
        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setNumber("Wagon 1");
        wagon.setType("Type 1");
        wagon.setTareWeight(10.0);
        wagon.setLoadCapacity(20.0);

        Wagon updatedWagon = new Wagon();
        updatedWagon.setId(1L);
        updatedWagon.setNumber("Updated Wagon 1");
        updatedWagon.setType("Updated Type 1");
        updatedWagon.setTareWeight(15.0);
        updatedWagon.setLoadCapacity(25.0);

        given(wagonRepository.findById(1L)).willReturn(Optional.of(wagon));
        given(wagonRepository.save(any(Wagon.class))).willReturn(updatedWagon);

        mockMvc.perform(put("/api/wagons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"number\": \"Updated Wagon 1\", \"type\": \"Updated Type 1\", \"tareWeight\": 15.0, \"loadCapacity\": 25.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedWagon.getId().intValue())))
                .andExpect(jsonPath("$.number", is(updatedWagon.getNumber())))
                .andExpect(jsonPath("$.type", is(updatedWagon.getType())))
                .andExpect(jsonPath("$.tareWeight", is(updatedWagon.getTareWeight())))
                .andExpect(jsonPath("$.loadCapacity", is(updatedWagon.getLoadCapacity())));
    }

    @Test
    public void testDeleteWagon() throws Exception {
        mockMvc.perform(delete("/api/wagons/1"))
                .andExpect(status().isOk());
        verify(wagonRepository, times(1)).deleteById(1L);
    }
}


