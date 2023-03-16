package com.getLicense.model;

import java.util.List;
import java.util.Map;

public class License {
    private String softwareName;
    private String softwareVersion;
    private String vendorName;
    private Map<String, String> additionalInformation;
    private List<SoftwareModule> softwareModules;

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Map<String, String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public List<SoftwareModule> getSoftwareModules() {
        return softwareModules;
    }

    public void setSoftwareModules(List<SoftwareModule> softwareModules) {
        this.softwareModules = softwareModules;
    }
}
