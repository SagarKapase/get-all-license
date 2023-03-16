package com.getLicense.model;

import java.util.List;

public class SoftwareModule {
    private String moduleName;
    private String moduleVersion;
    private SoftwareModuleInfo module;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion;
    }

    public SoftwareModuleInfo getModule() {
        return module;
    }

    public void setModule(SoftwareModuleInfo module) {
        this.module = module;
    }
}
