package com.andmobi.recuc_keybox.modle;

/**
 * Description:
 * Created by andmobi003 on 2016/8/19 16:24
 */
public class Base<T> {

    /**
     * status
     */
    Integer status;

    /**
     * meta 错误信息
     */
    String meta;


    T datas;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Base{" +
                "status=" + status +
                ", meta='" + meta + '\'' +
                ", datas=" + datas +
                '}';
    }
}
