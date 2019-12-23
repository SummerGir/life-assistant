package com.app.typeinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "app_type_detail", schema = "life-assistant", catalog = "")
public class AppTypeDetailEntity {
    private String typeDetailId;
    private String typeId;
    private String detailName;
    private String detailCode;
    private String detailValue;
    private int detailLevel;
    private String comment;
    private boolean isValid;

    @Id
    @Column(name = "TYPE_DETAIL_ID", nullable = false, length = 36)
    public String getTypeDetailId() {
        return typeDetailId;
    }

    public void setTypeDetailId(String typeDetailId) {
        this.typeDetailId = typeDetailId;
    }

    @Basic
    @Column(name = "TYPE_ID", nullable = false, length = 36)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "DETAIL_NAME", nullable = false, length = 100)
    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    @Basic
    @Column(name = "DETAIL_CODE", nullable = false, length = 50)
    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    @Basic
    @Column(name = "DETAIL_VALUE", nullable = false, length = 50)
    public String getDetailValue() {
        return detailValue;
    }

    public void setDetailValue(String detailValue) {
        this.detailValue = detailValue;
    }

    @Basic
    @Column(name = "DETAIL_LEVEL", nullable = false)
    public int getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(int detailLevel) {
        this.detailLevel = detailLevel;
    }

    @Basic
    @Column(name = "COMMENT", nullable = true, length = 1000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "IS_VALID", nullable = false)
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppTypeDetailEntity that = (AppTypeDetailEntity) o;

        if (detailLevel != that.detailLevel) return false;
        if (isValid != that.isValid) return false;
        if (typeDetailId != null ? !typeDetailId.equals(that.typeDetailId) : that.typeDetailId != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (detailName != null ? !detailName.equals(that.detailName) : that.detailName != null) return false;
        if (detailCode != null ? !detailCode.equals(that.detailCode) : that.detailCode != null) return false;
        if (detailValue != null ? !detailValue.equals(that.detailValue) : that.detailValue != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeDetailId != null ? typeDetailId.hashCode() : 0;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (detailName != null ? detailName.hashCode() : 0);
        result = 31 * result + (detailCode != null ? detailCode.hashCode() : 0);
        result = 31 * result + (detailValue != null ? detailValue.hashCode() : 0);
        result = 31 * result + detailLevel;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (isValid ? 1 : 0);
        return result;
    }
}
