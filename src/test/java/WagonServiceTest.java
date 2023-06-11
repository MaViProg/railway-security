import com.mavi.restrailwaysecurity.RestRailwaySecurityApplication;
import com.mavi.restrailwaysecurity.entity.StationTrack;
import com.mavi.restrailwaysecurity.entity.Wagon;
import com.mavi.restrailwaysecurity.entity.Waybill;
import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
import com.mavi.restrailwaysecurity.repository.WagonRepository;
import com.mavi.restrailwaysecurity.repository.WaybillRepository;
import com.mavi.restrailwaysecurity.service.WagonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;



@SpringBootTest(classes = RestRailwaySecurityApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class WagonServiceTest {

    @Autowired
    private WagonService wagonService;

    @Autowired
    private WagonRepository wagonRepository;

    @Autowired
    private StationTrackRepository stationTrackRepository;

    @Autowired
    private WaybillRepository waybillRepository;

    @Before
    public void setUp() {
        StationTrack stationTrack = new StationTrack();
        stationTrack.setId(1L);
        stationTrackRepository.save(stationTrack);

        Waybill waybill = new Waybill();
        waybillRepository.save(waybill);

        List<Wagon> wagons = new ArrayList<>();
        Wagon wagon1 = new Wagon("123", "type1", 100.0, 200.0, null);
        Wagon wagon2 = new Wagon("456", "type2", 150.0, 250.0, null);
        wagons.add(wagon1);
        wagons.add(wagon2);

        wagon1.setWaybill(waybill);
        wagon2.setWaybill(waybill);
        wagon1.setPosition(0);
        wagon2.setPosition(1);
        wagon1.setStationTrack(stationTrack);
        wagon2.setStationTrack(stationTrack);

        wagonRepository.saveAll(wagons);
    }

//    @Test
//    public void testReceiveWagonsEmptyStationTrack() {
//        List<Wagon> wagons = new ArrayList<>();
//        wagons.add(new Wagon("123", "flatbed", 10.0, 20.0, null));
//        wagons.add(new Wagon("456", "boxcar", 15.0, 25.0, null));
//        wagonService.receiveWagons(wagons, 1L);
//
//        List<Wagon> receivedWagons = wagonRepository.findByStationTrackIdOrderByPositionAsc(1L);
//        assertEquals(2, receivedWagons.size());
//        assertEquals("123", receivedWagons.get(0).getNumber());
//        assertEquals("flatbed", receivedWagons.get(0).getType());
//        assertEquals(10.0, receivedWagons.get(0).getTareWeight(), 0.0);
//        assertEquals(20.0, receivedWagons.get(0).getLoadCapacity(), 0.0);
//        assertEquals(0, receivedWagons.get(0).getPosition());
//        assertEquals("456", receivedWagons.get(1).getNumber());
//        assertEquals("boxcar", receivedWagons.get(1).getType());
//        assertEquals(15.0, receivedWagons.get(1).getTareWeight(), 0.0);
//        assertEquals(25.0, receivedWagons.get(1).getLoadCapacity(), 0.0);
//        assertEquals(1, receivedWagons.get(1).getPosition());
//    }

//    @Test
//    public void testMoveWagons() {
//        // Create test data
//        Long stationTrackId = 1L;
//        StationTrack stationTrack = new StationTrack();
//        stationTrack.setId(stationTrackId);
//
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon();
//        wagon1.setId(1L);
//        wagon1.setNumber("123");
//        wagon1.setType("boxcar");
//        wagons.add(wagon1);
//
//        Wagon wagon2 = new Wagon();
//        wagon2.setId(2L);
//        wagon2.setNumber("456");
//        wagon2.setType("flatcar");
//        wagons.add(wagon2);
//
//        Wagon firstWagon = new Wagon();
//        firstWagon.setId(3L);
//        firstWagon.setPosition(0);
//        when(wagonRepository.findTopByStationTrackOrderByPositionAsc(stationTrack)).thenReturn(firstWagon);
//
//        // Call the method under test
//        wagonService.moveWagons(wagons, stationTrackId);
//
//        // Verify the results
//        ArgumentCaptor<Wagon> wagonCaptor = ArgumentCaptor.forClass(Wagon.class);
//        verify(wagonRepository, times(2)).save(wagonCaptor.capture());
//
//        List<Wagon> savedWagons = wagonCaptor.getAllValues();
//        assertEquals(stationTrack, savedWagons.get(0).getStationTrack());
//        assertEquals(0, savedWagons.get(0).getPosition());
//        assertEquals(stationTrack, savedWagons.get(1).getStationTrack());
//        assertEquals(-1, savedWagons.get(1).getPosition());
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testMoveWagonsWithNullNumber() {
//        // Create test data
//        Long stationTrackId = 1L;
//        StationTrack stationTrack = new StationTrack();
//        stationTrack.setId(stationTrackId);
//
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon();
//        wagon1.setId(1L);
//        wagon1.setNumber(null);
//        wagon1.setType("boxcar");
//        wagons.add(wagon1);
//
//        // Call the method under test
//        wagonService.moveWagons(wagons, stationTrackId);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testMoveWagonsWithEmptyNumber() {
//        // Create test data
//        Long stationTrackId = 1L;
//        StationTrack stationTrack = new StationTrack();
//        stationTrack.setId(stationTrackId);
//
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon();
//        wagon1.setId(1L);
//        wagon1.setNumber("");
//        wagon1.setType("boxcar");
//        wagons.add(wagon1);
//
//        // Call the method under test
//        wagonService.moveWagons(wagons, stationTrackId);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testMoveWagonsWithNullType() {
//        // Create test data
//        Long stationTrackId = 1L;
//        StationTrack stationTrack = new StationTrack();
//        stationTrack.setId(stationTrackId);
//
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon();
//        wagon1.setId(1L);
//        wagon1.setNumber("123");
//        wagon1.setType(null);
//        wagons.add(wagon1);
//
//        // Call the method under test
//        wagonService.moveWagons(wagons, stationTrackId);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testMoveWagonsWithEmptyType() {
//        // Create test data
//        Long stationTrackId = 1L;
//        StationTrack stationTrack = new StationTrack();
//        stationTrack.setId(stationTrackId);
//
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon();
//        wagon1.setId(1L);
//        wagon1.setNumber("123");
//        wagon1.setType("");
//        wagons.add(wagon1);
//
//        // Call the method under test
//        wagonService.moveWagons(wagons, stationTrackId);
//    }

    @Test
    public void testDepartWagons() {
        // Create test data
        Long waybillId = 1L;
        Waybill waybill = new Waybill();
        waybill.setId(waybillId);

        Wagon wagon1 = new Wagon();
        wagon1.setId(1L);
        wagon1.setWaybill(waybill);
        wagon1.setPosition(1);

        Wagon wagon2 = new Wagon();
        wagon2.setId(2L);
        wagon2.setWaybill(waybill);
        wagon2.setPosition(2);

        when(waybillRepository.findById(waybillId)).thenReturn(Optional.of(waybill));
        when(wagonRepository.findTopByWaybillOrderByPositionAsc(waybill)).thenReturn(wagon1);

        // Call the method under test
        wagonService.departWagons(waybillId);

        // Verify the results
        verify(wagonRepository, times(1)).deleteAllByWaybill(waybill);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepartWagonsWithInvalidId() {
        // Create test data
        Long waybillId = 1L;

        when(waybillRepository.findById(waybillId)).thenReturn(Optional.empty());

        // Call the method under test
        wagonService.departWagons(waybillId);
    }

    @Test(expected = IllegalStateException.class)
    public void testDepartWagonsWithNoWagons() {
        // Create test data
        Long waybillId = 1L;
        Waybill waybill = new Waybill();
        waybill.setId(waybillId);

        when(waybillRepository.findById(waybillId)).thenReturn(Optional.of(waybill));
        when(wagonRepository.findTopByWaybillOrderByPositionAsc(waybill)).thenReturn(null);

        // Call the method under test
        wagonService.departWagons(waybillId);
    }

}


