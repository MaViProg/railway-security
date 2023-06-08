//import com.mavi.restrailwaysecurity.RestRailwaySecurityApplication;
//import com.mavi.restrailwaysecurity.entity.StationTrack;
//import com.mavi.restrailwaysecurity.entity.Wagon;
//import com.mavi.restrailwaysecurity.entity.Waybill;
//import com.mavi.restrailwaysecurity.repository.StationTrackRepository;
//import com.mavi.restrailwaysecurity.repository.WagonRepository;
//import com.mavi.restrailwaysecurity.repository.WaybillRepository;
//import com.mavi.restrailwaysecurity.service.WagonService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//
//@SpringBootTest(classes = RestRailwaySecurityApplication.class)
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//public class WagonServiceTest {
//
//    @Autowired
//    private WagonService wagonService;
//
//    @Autowired
//    private WagonRepository wagonRepository;
//
//    @Autowired
//    private StationTrackRepository stationTrackRepository;
//
//    @Autowired
//    private WaybillRepository waybillRepository;
//
//    @Test
//    public void testReceiveWagons() {
//        // Create a new station track
//        StationTrack stationTrack = new StationTrack();
//        stationTrackRepository.save(stationTrack);
//
//        // Create a list of wagons to receive
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon("123", "type1", 100.0, 200.0, null);
//        Wagon wagon2 = new Wagon("456", "type2", 150.0, 250.0, null);
//        wagons.add(wagon1);
//        wagons.add(wagon2);
//
//        // Receive the wagons on the station track
//        wagonService.receiveWagons(wagons, stationTrack.getId());
//
//        // Check that the wagons were added to the end of the train
//        List<Wagon> receivedWagons = (List<Wagon>) wagonRepository.findTopByStationTrackOrderByPositionAsc(stationTrack);
//        assertEquals(2, receivedWagons.size());
//        assertEquals(wagon1, receivedWagons.get(0));
//        assertEquals(wagon2, receivedWagons.get(1));
//    }
//
//    @Test
//    public void testMoveWagons() {
//        // Create a new station track
//        StationTrack stationTrack = new StationTrack();
//        stationTrackRepository.save(stationTrack);
//
//        // Create a list of wagons to move
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon("123", "type1", 100.0, 200.0, null);
//        Wagon wagon2 = new Wagon("456", "type2", 150.0, 250.0, null);
//        wagons.add(wagon1);
//        wagons.add(wagon2);
//
//        // Receive the wagons on the station track
//        wagonService.receiveWagons(wagons, stationTrack.getId());
//
//        // Move the wagons to the beginning of the train
//        wagonService.moveWagons(wagons, stationTrack.getId());
//
//        // Check that the wagons were moved to the beginning of the train
//        List<Wagon> movedWagons = wagonRepository.findByStationTrackOrderByPositionAsc(stationTrack);
//        assertEquals(2, movedWagons.size());
//        assertEquals(wagon2, movedWagons.get(0));
//        assertEquals(wagon1, movedWagons.get(1));
//    }
//
//    @Test
//    public void testDepartWagons() {
//        // Create a new waybill
//        Waybill waybill = new Waybill();
//        waybillRepository.save(waybill);
//
//        // Create a list of wagons to depart
//        List<Wagon> wagons = new ArrayList<>();
//        Wagon wagon1 = new Wagon("123", "type1", 100.0, 200.0, null);
//        Wagon wagon2 = new Wagon("456", "type2", 150.0, 250.0, null);
//        wagons.add(wagon1);
//        wagons.add(wagon2);
//
//        // Receive the wagons on a station track
//        StationTrack stationTrack = new StationTrack();
//        stationTrackRepository.save(stationTrack);
//        wagonService.receiveWagons(wagons, stationTrack.getId());
//
//        // Set the waybill for the wagons
//        wagon1.setWaybill(waybill);
//        wagon2.setWaybill(waybill);
//        wagonRepository.saveAll(wagons);
//
//        // Depart the wagons from the beginning of the train
//        wagonService.departWagons(waybill.getId());
//
//        // Check that the wagons were removed from the beginning of the train
//        List<Wagon> departedWagons = wagonRepository.findByWaybillOrderByPositionAsc(waybill);
//        assertTrue(departedWagons.isEmpty());
//    }
//
//}
