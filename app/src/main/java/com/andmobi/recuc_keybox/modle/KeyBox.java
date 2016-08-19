package com.andmobi.recuc_keybox.modle;

/**
 * Description:
 * Created by andmobi003 on 2015/9/24 17:10
 */
public class KeyBox {

    Integer id;
    /**
     * 所属小区
     */
    Integer viliageId;
    /**
     * 小区名称
     */
    String villageName;
    /**
     * 小区地址
     */
    String viliageAddress;
    /**
     * 钥匙箱具体位置
     */
    String boxAddress;
    /**
     * 编号
     */
    String boxNo;
    /**
     * 状态
     */
    Integer boxStatus;
    /**
     * 格子数
     */
    Integer boxCellMax;
    /**
     * 当前空格数
     */
    Integer boxCellCanuse;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViliageId() {
        return viliageId;
    }

    public void setViliageId(Integer viliageId) {
        this.viliageId = viliageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getViliageAddress() {
        return viliageAddress;
    }

    public void setViliageAddress(String viliageAddress) {
        this.viliageAddress = viliageAddress;
    }

    public String getBoxAddress() {
        return boxAddress;
    }

    public void setBoxAddress(String boxAddress) {
        this.boxAddress = boxAddress;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public Integer getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Integer boxStatus) {
        this.boxStatus = boxStatus;
    }

    public Integer getBoxCellMax() {
        return boxCellMax;
    }

    public void setBoxCellMax(Integer boxCellMax) {
        this.boxCellMax = boxCellMax;
    }

    public Integer getBoxCel() {
        return boxCellCanuse;
    }

    public void setBoxCel(Integer boxCellCanuse) {
        this.boxCellCanuse = boxCellCanuse;
    }

    @Override
    public String toString() {
        return "KeyBox{" +
                ", id=" + id +
                ", viliageId=" + viliageId +
                ", villageName='" + villageName + '\'' +
                ", viliageAddress='" + viliageAddress + '\'' +
                ", boxAddress='" + boxAddress + '\'' +
                ", boxNo='" + boxNo + '\'' +
                ", boxStatus=" + boxStatus +
                ", boxCellMax=" + boxCellMax +
                ", boxCellCanuse=" + boxCellCanuse +
                '}';
    }
}
