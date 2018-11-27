INSERT INTO `hospital`.`h_patient` (`patient_name`, `patient_gender`, `patient_age`, `patient_account_number`, `patient_status`, `patient_uuid`) VALUES ('duck', '0', '23', '1', '0', '1');

INSERT INTO `hospital`.`h_staff` (`staff_name`, `staff_age`, `staff_department`, `staff_type`, `staff_gender`, `staff_experience`, `staff_description`) VALUES ('doctor', '30', '1', '0', '0', '10', 'test doctor');
INSERT INTO `hospital`.`h_staff` (`staff_name`, `staff_age`, `staff_department`, `staff_type`, `staff_gender`, `staff_experience`, `staff_description`) VALUES ('nurse', '22', '2', '1', '1', '2', 'test nurse');

INSERT INTO `hospital`.`h_medicine` (`medicine_name`, `medicine_description`, `medicine_price`, `medicine_stock`, `medicine_type`) VALUES ('阿莫西林', '消炎药', '22.5', '100', '1');
INSERT INTO `hospital`.`h_medicine` (`medicine_name`, `medicine_description`, `medicine_price`, `medicine_stock`, `medicine_type`) VALUES ('头孢', '还是消炎药', '223', '20', '0');

INSERT INTO `hospital`.`h_ward` (`ward_room_number`, `ward_floor`, `ward_capacity`, `ward_type`) VALUES ('101', '1', '4', '1');
