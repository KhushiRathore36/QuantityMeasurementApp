package com.quantitymeasurement;

import com.quantitymeasurement.dto.*;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class QuantityMeasurementApplicationTests {

    @Mock
    public QuantityMeasurementRepository repository;

    @InjectMocks
    public QuantityMeasurementServiceImpl service;

    private QuantityInputDTO input;

    @BeforeEach
    public void setUp() {

        QuantityDTO q1 = new QuantityDTO();
        q1.setValue(2.0);  
        q1.setUnit("LITRE");
        q1.setMeasurementType("VolumeUnit");

        QuantityDTO q2 = new QuantityDTO();
        q2.setValue(500.0);  
        q2.setUnit("MIILILITRE");
        q2.setMeasurementType("VolumeUnit");

        input = new QuantityInputDTO();
        input.setThisQuantityDTO(q1);
        input.setThatQuantityDTO(q2);
    }

    // ADD (2L + 500ml = 2.5L)
    @Test
    public void testAdd() {
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        QuantityMeasurementEntity result = service.add(input);

        assertEquals("ADD", result.getOperation());
        assertEquals(2.5, result.getResultValue(), 0.01);
    }

    // SUBTRACT (2L - 500ml = 1.5L)
    @Test
    public void testSubtract() {
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        QuantityMeasurementEntity result = service.subtract(input);

        assertEquals("SUBTRACT", result.getOperation());
        assertEquals(1.5, result.getResultValue(), 0.01);
    }

    // CONVERT (2L → ml = 2000ml)
    @Test
    public void testConvert() {
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        QuantityMeasurementEntity result = service.convert(input);

        assertEquals("CONVERT", result.getOperation());
        assertEquals(2000.0, result.getResultValue(), 0.01);
    }

    // COMPARE (2L == 500ml → false)
    @Test
    public void testCompare() {
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        QuantityMeasurementEntity result = service.compare(input);

        assertEquals("COMPARE", result.getOperation());
        assertEquals("false", result.getResultString());
    }

	// HISTORY
	@Test
	public void testGetHistoryByOperation() {
	
	    List<QuantityMeasurementEntity> list = new ArrayList<>();
	    list.add(new QuantityMeasurementEntity());
	    list.add(new QuantityMeasurementEntity());
	
	    when(repository.findByOperation("ADD")).thenReturn(list);
	
	    List<QuantityMeasurementEntity> result = service.getHistoryByOperation("ADD");
	
	    assertEquals(2, result.size());
	}
	
	// COUNT
	@Test
	public void testGetOperationCount() {
	
	    when(repository.countByOperationAndErrorFalse("ADD")).thenReturn(3L);
	
	    long count = service.getOperationCount("ADD");
	
	    assertEquals(3, count);
	}
}
