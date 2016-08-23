package com.andmobi.recuc_keybox.modle;

/**
 * Description:
 * Created by andmobi003 on 2016/8/23 14:45
 */
public class UserOrder {




    /**
     * type 区分是否为一般订单和挪车订单
     */
    Integer type = 0;


    /**
     * 订单ID
     */
    Integer id;

    /**
     * 下单车主姓名
     */
    String ownerName;

    /**
     * 车信息
     */
    String carName;

    /**
     * 订单状态
     */
    Integer orderStatus;

    /**
     * 钥匙柜id
     */
    Integer keyboxId;

    /**
     * 钥匙柜标识码
     */
    String keyboxNo;

    //车牌
    String carNo;

    /**
     * 钥匙柜格子码
     */
    String keyboxCellNo, cellNo;

    int ownerViliageId, villageId;//订单的小区ID
    String ownerViliage;//订单的小区

    String orderStatusName;

    String orderNo;
    String villageName;
    String createDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserKey{" +
                "type=" + type +

                ", id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", carName='" + carName + '\'' +
                ", orderStatus=" + orderStatus +
                ", keyboxId=" + keyboxId +
                ", keyboxNo='" + keyboxNo + '\'' +
                ", carNo='" + carNo + '\'' +
                ", keyboxCellNo='" + keyboxCellNo + '\'' +
                ", cellNo='" + cellNo + '\'' +
                ", ownerViliageId=" + ownerViliageId +
                ", villageId=" + villageId +
                ", ownerViliage='" + ownerViliage + '\'' +
                ", orderStatusName='" + orderStatusName + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", villageName='" + villageName + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }

    public String getKeyboxNo() {
        return keyboxNo;
    }

    public void setKeyboxNo(String keyboxNo) {
        this.keyboxNo = keyboxNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOwnerViliage() {
        if (ownerViliage == null)
            return villageName;
        else
            return ownerViliage;
    }

    public void setOwnerViliage(String ownerViliage) {
        this.ownerViliage = ownerViliage;
    }

    public int getOwnerViliageId() {
        if (ownerViliageId == 0)
            return villageId;
        else
            return ownerViliageId;
    }

    public void setOwnerViliageId(int ownerViliageId) {
        this.ownerViliageId = ownerViliageId;
    }

    public String getOrderStatusName() {
        if (orderStatusName == null)
            return getorderActionStr();
        else

            return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCarName() {
        return carNo;

    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getKeyboxId() {
        return keyboxId;
    }

    public void setKeyboxId(Integer keyboxId) {
        this.keyboxId = keyboxId;
    }

    public String getKeyboxCellNo() {
        if (keyboxCellNo == null)
            return cellNo;
        else
            return keyboxCellNo;
    }

    public void setKeyboxCellNo(String keyboxCellNo) {
        this.keyboxNo = keyboxCellNo;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getorderActionStr() {
        switch (getOrderStatus()) {
            case -1:
                return "-";
            case 0:
                return "未付款";
            case 1:
                return "已付款";
            case 2:
                return "车主已存钥匙";
            case 3:
                return "已取钥匙";
            case 4:
                return "已还钥匙";
            case 5:
                return "车主已取钥匙，完成服务";
            default:
                return "-";
        }
    }
}
