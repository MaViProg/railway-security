import com.mavi.restrailwaysecurity.conroller.WaybillController;
import com.mavi.restrailwaysecurity.entity.Cargo;
import com.mavi.restrailwaysecurity.entity.Waybill;
import com.mavi.restrailwaysecurity.repository.WaybillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class WaybillControllerTest {

    @Mock
    private WaybillRepository waybillRepository;

    @InjectMocks
    private WaybillController waybillController;

    @Test
    public void testGetAllWaybills() {
        List<Waybill> waybills = new ArrayList<>();
        waybills.add(new Waybill());
        waybills.add(new Waybill());
        when(waybillRepository.findAll()).thenReturn(waybills);

        List<Waybill> result = waybillController.getAllWaybills();

        assertThat(result.size()).isEqualTo(2);
        verify(waybillRepository).findAll();
    }

    @Test
    public void testGetWaybillById() {
        Waybill waybill = new Waybill();
        when(waybillRepository.findById(1L)).thenReturn(Optional.of(waybill));

        Waybill result = waybillController.getWaybillById(1L);

        assertThat(result).isEqualTo(waybill);
        verify(waybillRepository).findById(1L);
    }

    @Test
    public void testGetWaybillByIdNotFound() {
        when(waybillRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> waybillController.getWaybillById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Waybill not found with id 1");
        verify(waybillRepository).findById(1L);
    }

    @Test
    public void testCreateWaybill() {
        Waybill waybill = new Waybill();
        when(waybillRepository.save(waybill)).thenReturn(waybill);

        Waybill result = waybillController.createWaybill(waybill);

        assertThat(result).isEqualTo(waybill);
        verify(waybillRepository).save(waybill);
    }



        @Test
        public void testUpdateWaybill() {
            // Create existing waybill
            Waybill existingWaybill = new Waybill();
            existingWaybill.setId(1L);
            existingWaybill.setCargo(new Cargo("TEST", "Test Cargo"));
            existingWaybill.setCargoWeight(1000.0);
            existingWaybill.setWagonWeight(2000.0);

            // Create new waybill
            Waybill newWaybill = new Waybill();
            newWaybill.setCargo(new Cargo("TEST", "Updated Cargo"));
            newWaybill.setCargoWeight(1500.0);
            newWaybill.setWagonWeight(2500.0);

            // Mock repository
            when(waybillRepository.findById(existingWaybill.getId()))
                    .thenReturn(Optional.of(existingWaybill));
            when(waybillRepository.save(any(Waybill.class)))
                    .thenReturn(newWaybill);

            // Update waybill
            Waybill updatedWaybill = waybillController.updateWaybill(newWaybill, existingWaybill.getId());

            // Verify the result
            assertNotNull(updatedWaybill);
            assertEquals(existingWaybill.getId(), updatedWaybill.getId());
            assertEquals(newWaybill.getCargo().getCode(), updatedWaybill.getCargo().getCode());
            assertEquals(newWaybill.getCargo().getName(), updatedWaybill.getCargo().getName());
            assertEquals(newWaybill.getCargoWeight(), updatedWaybill.getCargoWeight(), 0.0);
            assertEquals(newWaybill.getWagonWeight(), updatedWaybill.getWagonWeight(), 0.0);
        }



    @Test
    public void testUpdateWaybillNotFound() {
        Waybill newWaybill = new Waybill();
        Cargo updatedCargo = new Cargo("TEST", "Updated Cargo");
        newWaybill.setCargo(updatedCargo);
        newWaybill.setCargoWeight(1500.0);
        newWaybill.setWagonWeight(2500.0);
        when(waybillRepository.findById(1L)).thenReturn(Optional.empty());
        when(waybillRepository.save(newWaybill)).thenReturn(newWaybill);

        Waybill result = waybillController.updateWaybill(newWaybill, 1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getCargo().getCode()).isEqualTo("TEST");
        assertThat(result.getCargo().getName()).isEqualTo("Updated Cargo");
        assertThat(result.getCargoWeight()).isEqualTo(1500.0);
        assertThat(result.getWagonWeight()).isEqualTo(2500.0);
        verify(waybillRepository).findById(1L);
        verify(waybillRepository).save(newWaybill);
    }


    @Test
    public void testDeleteWaybill() {
        waybillController.deleteWaybill(1L);

        verify(waybillRepository).deleteById(1L);
    }
}
