package com.quantity.app;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.IQuantityMeasurementService;
import com.quantity.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementCacheRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 =
                new QuantityDTO(10, "FEET", "LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(5, "FEET", "LENGTH");

        controller.performAddition(q1, q2);

        controller.performComparison(q1, q2);
    }
}
