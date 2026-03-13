CREATE TABLE quantity_measurement_entity (

    id INT AUTO_INCREMENT PRIMARY KEY,
    value DOUBLE,
    unit VARCHAR(20),
    measurement_type VARCHAR(20),
    operation VARCHAR(20),
    created_at TIMESTAMP

);