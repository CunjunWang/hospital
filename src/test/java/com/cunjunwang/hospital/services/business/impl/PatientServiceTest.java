package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.HospitalServiceApplicationTests;
import com.cunjunwang.hospital.model.po.HPatient;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by CunjunWang on 2018/11/25.
 */
public class PatientServiceTest extends HospitalServiceApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceTest.class);

    @Autowired
    private PatientService patientService;

    @Test
    public void selectPatient() {
        Long patientId1 = 1L;
        HPatient patient1 = patientService.getOnePatientInfo(patientId1);
        logger.info("Patient Test, patient 1 info: {}", patient1.toString());
        Assert.assertEquals(patient1.getPatientName(), "duck");

        Long patientId2 = 2L;
        HPatient patient2 = patientService.getOnePatientInfo(patientId2);
        logger.info("Patient Test, patient 2 info: {}", patient2);
        Assert.assertNull(patient2);
    }
}