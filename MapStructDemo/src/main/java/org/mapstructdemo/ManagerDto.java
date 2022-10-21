package org.mapstructdemo;

public class ManagerDto {
    private int managerId;
    private String managerName;

    public ManagerDto() {
    }

    public ManagerDto(int managerId, String managerName) {
        this.managerId = managerId;
        this.managerName = managerName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return "ManagerDto{" +
                "managerId=" + managerId +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
