package com.app.cost.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "app_daily_cost_info", schema = "life-assistant", catalog = "")
public class AppDailyCostInfoEntity {
    private String costId;
    private String title;
    private Timestamp costTime;
    private BigDecimal costNum;
    private BigDecimal costPrice;
    private BigDecimal payMoney;
    private String typeDetailId;
    private Timestamp sysTime;

    @Id
    @Column(name = "COST_ID", nullable = false, length = 36)
    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "COST_TIME", nullable = true)
    public Timestamp getCostTime() {
        return costTime;
    }

    public void setCostTime(Timestamp costTime) {
        this.costTime = costTime;
    }

    @Basic
    @Column(name = "COST_NUM", nullable = true, precision = 4)
    public BigDecimal getCostNum() {
        return costNum;
    }

    public void setCostNum(BigDecimal costNum) {
        this.costNum = costNum;
    }

    @Basic
    @Column(name = "COST_PRICE", nullable = true, precision = 4)
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    @Basic
    @Column(name = "PAY_MONEY", nullable = true, precision = 4)
    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    @Basic
    @Column(name = "TYPE_DETAIL_ID", nullable = true, length = 36)
    public String getTypeDetailId() {
        return typeDetailId;
    }

    public void setTypeDetailId(String typeDetailId) {
        this.typeDetailId = typeDetailId;
    }

    @Basic
    @Column(name = "SYS_TIME", nullable = true)
    public Timestamp getSysTime() {
        return sysTime;
    }

    public void setSysTime(Timestamp sysTime) {
        this.sysTime = sysTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppDailyCostInfoEntity that = (AppDailyCostInfoEntity) o;

        if (costId != null ? !costId.equals(that.costId) : that.costId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (costTime != null ? !costTime.equals(that.costTime) : that.costTime != null) return false;
        if (costNum != null ? !costNum.equals(that.costNum) : that.costNum != null) return false;
        if (costPrice != null ? !costPrice.equals(that.costPrice) : that.costPrice != null) return false;
        if (payMoney != null ? !payMoney.equals(that.payMoney) : that.payMoney != null) return false;
        if (typeDetailId != null ? !typeDetailId.equals(that.typeDetailId) : that.typeDetailId != null) return false;
        if (sysTime != null ? !sysTime.equals(that.sysTime) : that.sysTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = costId != null ? costId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (costTime != null ? costTime.hashCode() : 0);
        result = 31 * result + (costNum != null ? costNum.hashCode() : 0);
        result = 31 * result + (costPrice != null ? costPrice.hashCode() : 0);
        result = 31 * result + (payMoney != null ? payMoney.hashCode() : 0);
        result = 31 * result + (typeDetailId != null ? typeDetailId.hashCode() : 0);
        result = 31 * result + (sysTime != null ? sysTime.hashCode() : 0);
        return result;
    }
}
