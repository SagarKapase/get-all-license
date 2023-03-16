package com.getLicense.controller;

import com.getLicense.model.License;
import com.getLicense.services.LicenseServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LicenseCtrl {
    @Autowired
    private LicenseServ licenseServ;
    //This mapping is for getting all license
    @GetMapping("/getAllLicense")
    public List<License> getAllLicenses(String softwareName, String softwareVersion, String vendorName) throws Exception {
        return licenseServ.getAllLicenses(softwareName, softwareVersion, vendorName);
    }
}
